package com.wifi.app.controllers;


import com.wifi.app.entity.Establishment;
import com.wifi.app.entity.TypeBusiness;
import com.wifi.app.objects.TypeBusinessDTO;
import com.wifi.app.service.EstablishmentService;
import com.wifi.app.service.TypeBusinessService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class TypeBusinessController {

    private static final Logger log = LoggerFactory.getLogger(TypeBusinessController.class);
    private final TypeBusinessService typeBusinessService;
    private final EstablishmentService establishmentService;

    @GetMapping("/typebusiness")
    public String list(Model model) {
        List<TypeBusiness> typeBusiness=typeBusinessService.getTypeBusinessList();
        model.addAttribute("typeBusiness", typeBusiness);
        return "typebusiness";
    }

    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);

    }

    @GetMapping("/register-typebusiness")
    public String register(@ModelAttribute TypeBusinessDTO typeBusinessDTO, Model model){
        model.addAttribute( "typeBusinessDTO", typeBusinessDTO);
        return "register-typebusiness";
    }

    @PostMapping("/register-typebusiness")
    public String save(@Validated TypeBusinessDTO typeBusinessDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        //1.1 valida si el cliente existe
        if(typeBusinessService.TypeBusinessExist(typeBusinessDTO.getName())){
            bindingResult.addError(new FieldError("typeBusinessDTO", "name",
                    "El tipo de negocio ya esta registrado"));
        }

        if(bindingResult.hasErrors()){
            return "register-typebusiness";
        }

        redirectAttributes.addFlashAttribute("message", "Tipo de negocio Registrado");
        typeBusinessService.register(typeBusinessDTO);
        return  "redirect:/register-typebusiness";
    }

    @PostMapping("/delete_type_business")
    public String delete(@Validated Integer  id, String name, RedirectAttributes redirectAttributes) {

        //valida si las sucursales tienen el tipo de negocio asignado

        List<Establishment> countTypeBusiness = establishmentService.findEstablishmentByType(name);

        if(countTypeBusiness.isEmpty()){
            log.info(">> Lista vacia : {}");
            log.info(">> se puede eliminar el id : {}", id);
            typeBusinessService.deleteTypeBusinessById(id);
            redirectAttributes.addFlashAttribute("message_succefull", "Tipo de negocio Eliminado");
        }else {
            log.info(">> no se puede eliminar el id : {}", id);
            redirectAttributes.addFlashAttribute("message", "No se puede eliminar el tipo de negocio, el tipo esta asociado a una o mas sucursales");
        }
        return "redirect:/typebusiness";

    }
}
