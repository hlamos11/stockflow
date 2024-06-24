package com.wifi.app.controllers;


import com.wifi.app.entity.Vendor;
import com.wifi.app.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);

    }

    @GetMapping("/vendor")
    public String getVendors (Model model){
        List<Vendor> list = vendorService.getFindVendorAll();
        model.addAttribute("dataVendor",list);
        return "vendor";
    }
}
