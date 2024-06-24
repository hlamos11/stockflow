package com.wifi.app.controllers;


import com.wifi.app.entity.Clients;
import com.wifi.app.objects.CapitalizationDTO;
import com.wifi.app.service.CapitalizationsService;
import com.wifi.app.service.ClientService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CapitalizationsController {

    private static final Logger log = LoggerFactory.getLogger(CapitalizationsController.class);

    private final CapitalizationsService capitalizationsService;
    private final ClientService clientService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);

    }

    @GetMapping("/capitalizations")
    public String search (Model model) {

        //model.addAttribute("dataCapitalizations", capitalizationsService.getCapitalizationsList());
        model.addAttribute("dataCapitalizations", clientService.getClientList());
        return "capitalizations";
    }

    @GetMapping("/register-capitalizations")
    public String register(@ModelAttribute CapitalizationDTO capitalizationDTO, Model model){

        List<Clients> clients = clientService.getClientList();
        model.addAttribute("client", clients);

        model.addAttribute( "capitalizationDTO", capitalizationDTO);
        return "register-capitalizations";
    }

    @PostMapping("/register-capitalizations")
    public String save(@Validated CapitalizationDTO capitalizationDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        //log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>", capitalizationDTO.orden());
        //valida si el cliente existe
        if(capitalizationsService.clientExists(capitalizationDTO.getOrden())){
            bindingResult.addError(new FieldError("capitalizationDTO", "orden",
                    "La capitalizacion ya esta registrada"));
        }

        if(bindingResult.hasErrors()){
            return "register-capitalizations";
        }

        redirectAttributes.addFlashAttribute("message_succefull", "Capitalizacion Registrada");
        log.info(">> clientDTO : {}", capitalizationDTO.toString());
        capitalizationsService.register(capitalizationDTO);
        return  "redirect:/register-capitalizations";
    }


}
