package com.wifi.app.controllers;


import com.wifi.app.entity.EstablishmentDetail;
import com.wifi.app.entity.ModelAp;
import com.wifi.app.entity.ModelSwitch;
import com.wifi.app.objects.ModelApDTO;
import com.wifi.app.objects.ModelSwitchDTO;
import com.wifi.app.service.EstablishmentDetailService;
import com.wifi.app.service.EstablishmentService;
import com.wifi.app.service.ModelApService;
import com.wifi.app.service.ModelSwitchService;
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
public class ModelsController {

    private static final Logger log = LoggerFactory.getLogger(ModelsController.class);
    private final ModelApService modelApService;
    private final ModelSwitchService modelSwitchService;
    private final EstablishmentService establishmentService;
    private final EstablishmentDetailService establishmentDetailService;

    @Autowired
    public ModelsController(ModelApService modelApService, ModelSwitchService modelSwitchService, EstablishmentService establishmentService, EstablishmentDetailService establishmentDetailService) {
        this.modelApService = modelApService;
        this.modelSwitchService = modelSwitchService;
        this.establishmentService = establishmentService;
        this.establishmentDetailService = establishmentDetailService;
    }

    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping("/modelaps")
    public String listAps(Model model) {
        List<ModelAp> aps=modelApService.getModelApList();
        model.addAttribute("modelap",aps);
        return "modelaps";
    }

    @GetMapping("/modelswitch")
    public String listSwitch(Model model) {
        List<ModelSwitch> Switch=modelSwitchService.getModelSwitchList();
        model.addAttribute("modelswitch",Switch);
        return "modelswitch";
    }

    @GetMapping("/register-model-ap")
    public String register(@ModelAttribute ModelApDTO modelApDTO, Model model){
        model.addAttribute( "modelApDTO", modelApDTO);
        return "register-model-ap";
    }

    @GetMapping("/register-model-switch")
    public String register(@ModelAttribute ModelSwitchDTO modelSwitchDTO, Model model){
        model.addAttribute( "modelSwitchDTO", modelSwitchDTO);
        return "register-model-switch";
    }

    @PostMapping("/register-model-ap")
    public String save(@Validated ModelApDTO modelApDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        log.info(">> modelApDTO HLA : {}", modelApDTO.toString());
        //1.1 valida si el cliente existe
        if(modelApService.ModelApExist(modelApDTO.getModelap())){
            bindingResult.addError(new FieldError("modelApDTO", "modelap",
                    "El modelo de AP ya esta registrado"));
        }

        if(bindingResult.hasErrors()){
            return "register-model-ap";
        }

        redirectAttributes.addFlashAttribute("message", "Modelo de AP Registrado");
        log.info(">> modelApDTO : {}", modelApDTO.toString());
        modelApService.register(modelApDTO);
        return  "redirect:/register-model-ap";
    }

    @PostMapping("/register-model-switch")
    public String save(@Validated ModelSwitchDTO modelSwitchDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        //1.1 valida si el cliente existe
        if(modelSwitchService.ModelSwitchExist(modelSwitchDTO.getModelswitch())){
            bindingResult.addError(new FieldError("modelSwitchDTO", "modelswitch",
                    "El modelo de Switch ya esta registrado"));
        }

        if(bindingResult.hasErrors()){
            return "register-model-switch";
        }

        redirectAttributes.addFlashAttribute("message", "Modelo de Switch Registrado");
        log.info(">> modelSwitchDTO : {}", modelSwitchDTO.toString());
        modelSwitchService.register(modelSwitchDTO);
        return  "redirect:/register-model-switch";
    }

    @PostMapping("/delete_model_ap")
    public String delete(@Validated Integer  id, String modelap, RedirectAttributes redirectAttributes){
        log.info(">> id : {}", id);
        log.info(">> modelap : {}", modelap);

        List<EstablishmentDetail> countModelap = establishmentDetailService.findEstablishmentDetailByModelap(modelap);

        if(countModelap.isEmpty()) {

            modelApService.deleteModelApById(id);
            redirectAttributes.addFlashAttribute("message_succefull", "Modelo de AP Eliminado");
        }else{
            log.info(">> no se puede eliminar el id : {}", id);
            redirectAttributes.addFlashAttribute("message", "No se puede eliminar el modelo de AP, el AP esta asociado a una o mas sucursales");
        }
        return "redirect:/modelaps";
    }

    @PostMapping("/delete_model_switch")
    public String deleteSwitch(@Validated Integer  id, String modelswitch, RedirectAttributes redirectAttributes){
        log.info(">> id : {}", id);
        log.info(">> modelswitch : {}", modelswitch);

        List<EstablishmentDetail> countModelswitch= establishmentDetailService.findEstablishmentDetailByModelswitch(modelswitch);

        if(countModelswitch.isEmpty()) {
            modelSwitchService.deleteModelSwitchById(id);
            redirectAttributes.addFlashAttribute("message_succefull", "Modelo de Switch Eliminado");
        }else{
            log.info(">> no se puede eliminar el id : {}", id);
            redirectAttributes.addFlashAttribute("message", "No se puede eliminar el modelo, el Switch esta asociado a una o mas sucursales");
        }
        return "redirect:/modelswitch";
    }

}
