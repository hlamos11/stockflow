package com.wifi.app.controllers;

import com.wifi.app.entity.OrderLicensesMeraki;
import com.wifi.app.objects.OrderLicensesMerakiDTO;
import com.wifi.app.service.OrderLicensesMerakiService;
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
public class LicenciasMerakiController {

    private static final Logger log = LoggerFactory.getLogger(LicenciasMerakiController.class);

    private final OrderLicensesMerakiService orderLicensesMerakiService;

    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);

    }

    @GetMapping("/register-meraki")
    public String registerLicensesMeraki (@ModelAttribute OrderLicensesMerakiDTO orderLicensesMerakiDTO, Model model) {
        model.addAttribute( "orderLicensesMerakiDTO", orderLicensesMerakiDTO);
        return "register-meraki";
    }



    @GetMapping("/ordersMeraki")
    public String ordersMeraki (Model model) {
        List<OrderLicensesMeraki> orderLicensesMerakiList = orderLicensesMerakiService.getOrderLicensesMerakiList();
        model.addAttribute("dataOrderLicMeraki", orderLicensesMerakiList);

        return "ordersMeraki";
    }



    @PostMapping("/register-meraki")
    public String save(@Validated OrderLicensesMerakiDTO orderLicensesMerakiDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        //log.info("********************************************************************************************************");

        //log.info(">> orderLicensesMerakiDTO : {}", orderLicensesMerakiDTO.toString());
        //1.1 valida la orden existe
        if(orderLicensesMerakiService.orderExist(orderLicensesMerakiDTO.getOrdernumber())){
            bindingResult.addError(new FieldError("orderLicensesMerakiDTO", "ordernumber",
                    "El numero de orden ya esta registrada"));
        }

        if(bindingResult.hasErrors()){
            return "register-meraki";
        }

        redirectAttributes.addFlashAttribute("message_succefull", "Orden Registrada");
        //log.info(">> orderLicensesMerakiDTO : {}", orderLicensesMerakiDTO.toString());
        orderLicensesMerakiService.register(orderLicensesMerakiDTO);
        return  "redirect:/ordersMeraki";
    }
}
