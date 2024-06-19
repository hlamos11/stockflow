package com.wifi.app.controllers;

import com.wifi.app.entity.Brand;
import com.wifi.app.objects.BrandDTO;
import com.wifi.app.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BrandController {

    private final BrandService brandService;
    private static final Logger log = LoggerFactory.getLogger(BrandController.class);


    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);

    }

    @GetMapping("/brand")
    public String search(Model model){

        List<Brand> brandList = brandService.getBrandListAll();
        model.addAttribute("brandList", brandList);

        return "brand";

    }

    @GetMapping("/register-brand")
    public String register(@ModelAttribute BrandDTO brandDTO, Model model){

        model.addAttribute("brandDTO", brandDTO);

        return "register-brand";

    }

    @PostMapping("/register-brand")
    public String save(@Validated BrandDTO brandDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        brandDTO.setUser("CAMBIAR");

        if(brandService.storeExists(brandDTO.getName())){
            bindingResult.addError(new FieldError("brandDTO", "name",
                    "El fabricante ya esta registrado"));
        }

        if(bindingResult.hasErrors()){
            return "register-brand";
        }


        redirectAttributes.addFlashAttribute("message", "Fabricante Registrado");
        log.info(">> brandDTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO : {}", brandDTO.toString());
        brandService.register(brandDTO);

        return "redirect:/register-brand";

    }

    @PostMapping("/delete_brand")
    public String delete(Model model){

        return "brand";

    }
}
