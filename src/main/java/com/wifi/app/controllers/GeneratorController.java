package com.wifi.app.controllers;


import com.wifi.app.entity.MobilGenerator;
import com.wifi.app.entity.MobilGeneratorDetail;
import com.wifi.app.entity.Province;
import com.wifi.app.entity.Site;
import com.wifi.app.objects.*;
import com.wifi.app.service.*;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DecimalFormat;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequiredArgsConstructor
public class GeneratorController {

    private static final Logger log = LoggerFactory.getLogger(GeneratorController.class);
    private  String username;
    private final GeneratorService generatorService;
    private final MobilGeneratorDetailService mobilGeneratorDetailService;
    private final ProvinceService provinceService;
    private final SiteService siteService;
    //private static int idGenerator,currentLevel;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    private MobilGenerator mobilGenerator;

    @Autowired
    QueryService queryservice;

    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);

    }

    @GetMapping("/generator-management")
    public String getInit(@ModelAttribute generatorIdDTO generatorIdDTO, Model model){

        username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<MobilGenerator>  mobilGenerators = generatorService.getListMobilGenerator();
        model.addAttribute("mobilGenerators",mobilGenerators);
        model.addAttribute( "generatorIdDTO", generatorIdDTO);
        return "generator-management";
    }

    @GetMapping("/register-generator")
    public String getGenerator(@ModelAttribute GeneratorRegisterDTO generatorRegisterDTO, Model model){

        List<Site> sites = siteService.findAllByOrderByIdDesc();
        List<Province> provinces = provinceService.getProvinceList();

        model.addAttribute( "generatorRegisterDTO", generatorRegisterDTO);
        model.addAttribute( "sites", sites);
        model.addAttribute( "provinces", provinces);

        return "register-generator";
    }

    @GetMapping("/register-generator-detail")
    public String getGeneratorDetail(@ModelAttribute MobilGenerator getMobilGeneratorId, Model model, GeneratorDetailDTO generatorDetailDTO){

        mobilGenerator = generatorService.findMobilGeneratorById(getMobilGeneratorId.getId());

        generatorDetailDTO.setMobilGeneratorId(mobilGenerator.getId());
        model.addAttribute( "generatorDetailDTO", generatorDetailDTO);
        model.addAttribute("capTank", mobilGenerator.getTankLevel());
        model.addAttribute("idTank", mobilGenerator.getSite().getId());

        return "register-generator-detail";
    }






    @PostMapping("/register-generator")
    public String registerGenerator(@Validated GeneratorRegisterDTO data, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            return "register-generator";
        }

        try {


            GeneratorDTO generatorDTO = new GeneratorDTO();
            GeneratorDetailDTO generatorDetailDTO = new GeneratorDetailDTO();

            Integer valCurrentLevel, valRefill;

            valCurrentLevel = (data.getCurrentLevel() + data.getRefill()) * 100 / data.getTankLevel();
            valRefill = data.getTankLevel() - (data.getCurrentLevel() + data.getRefill()) ;

            generatorDTO.setUser(username);
            generatorDTO.setTankLevel(data.getTankLevel());
            generatorDTO.setCurrentLevel(valCurrentLevel);
            generatorDTO.setRefill(valRefill);
            generatorDTO.setSiteId(data.getSiteId());
            generatorDTO.setProvinceId(data.getProvinceId());

            MobilGenerator mobilGenerator1 = generatorService.register(generatorDTO);

            generatorDetailDTO.setUser(username);
            generatorDetailDTO.setHoursWorked(data.getHoursWorked());
            generatorDetailDTO.setPreviousLevel(data.getCurrentLevel());
            generatorDetailDTO.setCurrentLevel(data.getCurrentLevel() + data.getRefill());
            generatorDetailDTO.setRefill(data.getRefill());
            generatorDetailDTO.setEstimatedAmount(data.getEstimatedAmount());
            generatorDetailDTO.setMobilGeneratorId(mobilGenerator1.getId());
            generatorDetailDTO.setHourMeter(data.getHourMeter());
            generatorDetailDTO.setDate(data.getDate());
            generatorDetailDTO.setHoursWorked(data.getHoursWorked());

            mobilGeneratorDetailService.register(generatorDetailDTO);

            redirectAttributes.addFlashAttribute("message", "Registrado");
            return  "redirect:/generator-management";

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }


    @PostMapping("/register-generator-detail")
    public String register(@Validated GeneratorDetailDTO generatorDetailDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){


        if(bindingResult.hasErrors()){
            return "register-generator-detail";
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        MobilGeneratorDetail mobilGeneratorDetail  = mobilGeneratorDetailService.getMaxRegister(mobilGenerator.getId());



        generatorDetailDTO.setUser(username);
        generatorDetailDTO.setHoursWorked(generatorDetailDTO.getHourMeter() - mobilGeneratorDetail.getHourMeter());
        generatorDetailDTO.setPreviousLevel(generatorDetailDTO.getCurrentLevel());
        generatorDetailDTO.setCurrentLevel((generatorDetailDTO.getRefill() + generatorDetailDTO.getCurrentLevel()));
        generatorDetailDTO.setMobilGeneratorId(mobilGenerator.getId());

        //generatorDetailDTO.setEstimatedAmount(Double.valueOf(df.format(generatorDetailDTO.getRefill() * 3.28)));
        //mobilGenerator.setCurrentLevel(mobilGeneratorDetail.getCurrentLevel() * 100 / mobilGenerator.getTankLevel());
        //mobilGenerator.setRefill((mobilGenerator.getRefill() + mobilGeneratorDetail.getCurrentLevel()) - mobilGenerator.getTankLevel());


        MobilGeneratorDetail resp = mobilGeneratorDetailService.register(generatorDetailDTO);
        mobilGenerator.setCurrentLevel(resp.getCurrentLevel() * 100 / mobilGenerator.getTankLevel());
        mobilGenerator.setRefill(mobilGenerator.getTankLevel() - resp.getCurrentLevel());


        generatorService.save(mobilGenerator);

        redirectAttributes.addFlashAttribute("message", "Registrado");
        return  "redirect:/generator-management";
    }



    @RequestMapping(value="/searchGeneratorHistById", params = "id", method = GET)
    @ResponseBody
    public String searchGeneratorHistById(@RequestParam("id") int id){


        try{

            List<GeneratorHistDTO> list = queryservice.JPQLQueryGeneratorHist(id);
            //System.out.println("searchGeneratorHistById: " + list);

            JSONArray jsonArrayGeneratorHist = new JSONArray();

            JSONObject jsonObject = new JSONObject ();

            jsonArrayGeneratorHist.put(list);

            jsonObject.put("ListGeneratorHist", jsonArrayGeneratorHist);

            //log.info(">> hlamos : {}",jsonObject.toString());

            return jsonObject.toString();

        } catch (Exception e) {
            log.info("RequestMapping | searchGeneratorHistById - Error: ",e);
            throw new RuntimeException(e);
        }

    }




}
