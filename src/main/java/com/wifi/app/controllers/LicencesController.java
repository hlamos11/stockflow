package com.wifi.app.controllers;


import com.wifi.app.entity.*;
import com.wifi.app.objects.LicenseMerakiDTO;
import com.wifi.app.objects.LicensePortalDTO;
import com.wifi.app.service.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LicencesController {

    private static final Logger log = LoggerFactory.getLogger(LicencesController.class);

    private final EstablishmentService establishmentService;
    private final LicensePortalService licensePortalService;
    private final LicenseMerakiService licenseMerakiService;
    private final ClientService clientService;
    private final OrderLicensesPortalService orderLicensesPortalService;

    private final OrderLicensesMerakiService orderLicensesMerakiService;

    private final EstablishmentDetailService establishmentDetailService;


    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }


    @GetMapping("/licenses")
    public String search(Model model) {
        List<Clients> clientsList = clientService.findClientByTypeClientId(1);
        List<LicensePortal> dataLicensesPortal = licensePortalService.getLicensePortal();


        //model.addAttribute("dataLicensesPortal",dataLicensesPortal );
        model.addAttribute("clientsList", clientsList);
        return "licenses";
    }

    @GetMapping("/lic-portal")
    public String getLicPortal(Model model) {
        List<LicensePortal> dataLicensePortal = licensePortalService.getLicensePortal();
        model.addAttribute("dataLicensePortal",dataLicensePortal );
        return "lic-portal";
    }

    @GetMapping("/lic-meraki")
    public String getLicMeraki(Model model) {
        List<LicenseMeraki> dataLicensesMeraki = licenseMerakiService.findLicensesMerakiAll();
        model.addAttribute("dataLicensesMeraki",dataLicensesMeraki );
        return "lic-meraki";
    }

    @GetMapping("/register-lic-portal")
    public String register(@ModelAttribute LicensePortalDTO licensePortalDTO, Model model) {

        List<Clients> clientsList = clientService.findClientByTypeClientId(1);
        List<Establishment> establishmentList = new ArrayList<>();
        List<EstablishmentDetail> establishmentDetailList = new ArrayList<>();


        for (Iterator<Clients> i = clientsList.iterator(); i.hasNext(); ) {
            Clients item = i.next();
           // System.out.println("*********************item.getId()*******************************************************"+item.getId());
            Establishment establishment = establishmentService.findEstablishmentByClientId(item.getId());
            establishmentList.add(establishment);
            //System.out.println("*****************************************************************************"+establishmentList.toString());
        }

        for (Iterator<Establishment> i = establishmentList.iterator(); i.hasNext(); ) {
            Establishment item = i.next();
            //System.out.println("*****************************************************************************"+ item.getId());
            EstablishmentDetail establishmentDetail = establishmentDetailService.findEstablishmentDetailByEstablishmentId(item.getId());
            establishmentDetailList.add(establishmentDetail);
            //System.out.println("*****************************************************************************"+establishmentDetailList.toString());
        }

        model.addAttribute("licensePortalDTO", licensePortalDTO);
        model.addAttribute("dataClient", clientsList);
        model.addAttribute("dataEstablishment", establishmentList);
        model.addAttribute("dataEstablishmentDetail", establishmentDetailList);
        model.addAttribute("dataOrder", orderLicensesPortalService.getOrderLicensesPortalList());

        return "register-lic-portal";
    }

    @PostMapping("/register-lic-portal")
    public String save(@Validated LicensePortalDTO licensePortalDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        redirectAttributes.addFlashAttribute("message_succefull", "Licencia Registrada");
        //log.info(">> licensePortalDTO : {}", licensePortalDTO.toString());
        licensePortalService.register(licensePortalDTO);
        //log.info("################################################ licensePortalDTO", licensePortalDTO.toString());
        return "redirect:/register-lic-portal";
    }


    @GetMapping("/register-lic-meraki")
    public String registerMeraki(@ModelAttribute LicenseMerakiDTO licenseMerakiDTO, Model model) {

        List<Clients> clientsList = clientService.findClientByTypeClientId(1);
        List<Establishment> establishmentList = new ArrayList<>();
        List<EstablishmentDetail> establishmentDetailList = new ArrayList<>();


        for (Iterator<Clients> i = clientsList.iterator(); i.hasNext(); ) {
            Clients item = i.next();
            Establishment establishment = establishmentService.findEstablishmentByClientId(item.getId());
            establishmentList.add(establishment);
        }

        for (Iterator<Establishment> i = establishmentList.iterator(); i.hasNext(); ) {
            Establishment item = i.next();
            EstablishmentDetail establishmentDetail = establishmentDetailService.findEstablishmentDetailByEstablishmentId(item.getId());
            establishmentDetailList.add(establishmentDetail);
        }

        model.addAttribute("licenseMerakiDTO", licenseMerakiDTO);
        model.addAttribute("dataClient", clientsList);
        model.addAttribute("dataEstablishment", establishmentList);
        model.addAttribute("dataEstablishmentDetail", establishmentDetailList);
        model.addAttribute("dataOrder", orderLicensesMerakiService.getOrderLicensesMerakiList());

        return "register-lic-meraki";
    }

    @PostMapping("/register-lic-meraki")
    public String saveMeraki(@Validated LicenseMerakiDTO licenseMerakiDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        redirectAttributes.addFlashAttribute("message_succefull", "Licencia Registrada");
        //log.info(">> licensePortalDTO : {}", licensePortalDTO.toString());
        //licensePortalService.register(licensePortalDTO);
        //log.info("################################################ licensePortalDTO", licensePortalDTO.toString());
        return "redirect:/register-lic-meraki";
    }


}
