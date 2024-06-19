package com.wifi.app.controllers;


import com.wifi.app.entity.OrderLicensesPortal;
import com.wifi.app.objects.OrderLicensesPortalDTO;
import com.wifi.app.service.OrderLicensesPortalService;
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
public class LicenciasPortalController {

    private final OrderLicensesPortalService orderLicensesPortalService;
    private static final Logger log = LoggerFactory.getLogger(LicenciasPortalController.class);

    public LicenciasPortalController(OrderLicensesPortalService orderLicensesPortalService) {
        this.orderLicensesPortalService = orderLicensesPortalService;
    }

    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);

    }

    @GetMapping("/register-portal")
    public String registerLicensesPortal (@ModelAttribute OrderLicensesPortalDTO orderLicensesPortalDTO, Model model) {
        model.addAttribute( "orderLicensesPortalDTO", orderLicensesPortalDTO);
        return "register-portal";
    }

    @GetMapping("/ordersPortal")
    public String ordersPortal (Model model) {
        List<OrderLicensesPortal> orderLicensesPortalsList = orderLicensesPortalService.getOrderLicensesPortalList();
        model.addAttribute("dataOrderLicPortal", orderLicensesPortalsList);

        return "ordersPortal";
    }


    @PostMapping("/register-portal")
    public String save(@Validated OrderLicensesPortalDTO orderLicensesPortalDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        //log.info("********************************************************************************************************");

        //log.info(">> orderLicensesPortalDTO : {}", orderLicensesPortalDTO.toString());
        //1.1 valida la orden existe
        if(orderLicensesPortalService.orderExist(orderLicensesPortalDTO.getOrdernumber())){
            bindingResult.addError(new FieldError("orderLicensesPortalDTO", "ordernumber",
                    "El numero de orden ya esta registrada"));
        }

        if(bindingResult.hasErrors()){
            return "register-portal";
        }

        redirectAttributes.addFlashAttribute("message_succefull", "Orden Registrada");
        //log.info(">> orderLicensesMerakiDTO : {}", orderLicensesPortalDTO.toString());
        orderLicensesPortalService.register(orderLicensesPortalDTO);
        return  "redirect:/ordersPortal";
    }
}
