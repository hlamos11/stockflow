package com.wifi.app.controllers;


import com.wifi.app.entity.Responsible;
import com.wifi.app.objects.ResponsibleMaterialDTO;
import com.wifi.app.service.ResponsibleMaterialService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequiredArgsConstructor
public class ResponsibleController {

    private final ResponsibleMaterialService responsibleMaterialService;
    private static final Logger log = LoggerFactory.getLogger(ResponsibleController.class);


    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);

    }

    @GetMapping("/responsible-management")
    public String getResponsibles(Model model){

        List<Responsible> responsibleList = responsibleMaterialService.getResponsibleList();

        model.addAttribute("responsibleList", responsibleList);

        return "responsible-management";
    }

    @GetMapping("/register-responsible")
    public String registerResponsible(@ModelAttribute ResponsibleMaterialDTO responsibleMaterialDTO, Model model){

        model.addAttribute("responsibleMaterialDTO", responsibleMaterialDTO);

        return "register-responsible";
    }

    @PostMapping("/register-responsible")
    public String saveResponsible(@Validated ResponsibleMaterialDTO responsibleMaterialDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(responsibleMaterialService.getResponsibleExist(responsibleMaterialDTO.getEmail())){
            bindingResult.addError(new FieldError("responsibleMaterialDTO", "email",
                    "El email ya esta registrado"));
        }

        if(bindingResult.hasErrors()){
            return "register-responsible";
        }

        redirectAttributes.addFlashAttribute("message", "Responsable Registrado");

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        responsibleMaterialDTO.setUser(username);

        //log.info(">> responsibleMaterialDTO : {}", responsibleMaterialDTO.toString());
        responsibleMaterialService.register(responsibleMaterialDTO);

        return "redirect:/responsible-management";

    }
}
