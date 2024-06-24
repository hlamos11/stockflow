package com.wifi.app.controllers;

import com.wifi.app.entity.*;
import com.wifi.app.objects.EstablishmentDetailDTO;
import com.wifi.app.objects.EstablishmentsDTO;
import com.wifi.app.objects.SucursalDetail;
import com.wifi.app.repository.ClientRepository;
import com.wifi.app.repository.EstablishmentsRepository;
import com.wifi.app.service.*;
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

import java.math.BigInteger;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EstablishmentsController {

    @Autowired
    QueryService queryservice;

    @Autowired
    ClientRepository clientRepository;

    private static final Logger log = LoggerFactory.getLogger(EstablishmentsController.class);
    private final EstablishmentService establishmentService;
    private final ClientService clientService;
    private final ProvinceService provinceService;
    private final ProductService productService;
    private final ModelApService modelApService;
    private final ModelSwitchService modelSwitchService;
    private final TypeBusinessService typeBusinessService;

    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);

    }

    @GetMapping("/establishments")
    public String list(Model model){


        //model.addAttribute("details", clientRepository.findAll());
        model.addAttribute("clients", clientRepository.findAll());


        /*List<Establishments> establishments=establishmentService.getEstablishmentsList();
        log.info(">> establishments********************** : {}",establishments);
        model.addAttribute("establishments",establishments);*/


        /*List<SucursalDetail> detail = queryservice.JPQLQuery();
        log.info(">>  sucursales    ********************** : {}",detail);
        model.addAttribute("detail",detail);*/

        return "establishments";
    }


    @GetMapping("/register-establishments")
    public String register(@ModelAttribute EstablishmentsDTO establishmentsDTO, Model model){


        List<Clients> clients = clientService.getClientList();
        List<Province> province = provinceService.getProvinceList();
        List<Product> products= productService.getProductList();
        List<ModelAp> aps=modelApService.getModelApList();
        List<ModelSwitch> Switch=modelSwitchService.getModelSwitchList();
        List<TypeBusiness> typeBusiness = typeBusinessService.getTypeBusinessList();

        model.addAttribute( "establishmentsDTO", establishmentsDTO);
        model.addAttribute("client", clients);
        model.addAttribute("province", province);
        model.addAttribute("product", products);
        model.addAttribute("aps",aps);
        model.addAttribute("switch",Switch);
        model.addAttribute("typebusiness",typeBusiness);

        return "register-establishments";
    }


    @PostMapping("/register-establishments")
    public String save(@Validated EstablishmentsDTO establishmentsDTO, @Validated EstablishmentDetailDTO table_detail_establish , BindingResult bindingResult, RedirectAttributes redirectAttributes){

        //log.info(">> establishmentsDTO : {}", establishmentsDTO.toString());
        //1.1 valida si la sucursal existe para el cliente seleccionado

        //log.info(">> ########################################################################################### table_detail_establish : {}", table_detail_establish.toString());

        //log.info(">> establishmentsDTO.getIdclient() : {}", establishmentsDTO.getClientId());
        //log.info(">> establishmentsDTO.getNameestablishment() : {}", establishmentsDTO.getNameestablishment());
        //List<Establishment> countEstablishments = establishmentService.findAll(establishmentsDTO.getIdclient(), establishmentsDTO.getNameestablishment());
        //Integer countEstablishments = establishmentService.findAll(establishmentsDTO.getIdclient(), establishmentsDTO.getNameestablishment());


        BigInteger count = (BigInteger) queryservice.JPQLQueryEstablishments(establishmentsDTO.getClientId(),establishmentsDTO.getNameestablishment() );

        //log.info(">> count_establecimiento : ", count);

        if(count.compareTo(BigInteger.ZERO) > 0){
            log.info(">> establishmentsDTO : NO SE PUEDE REGISTRAR LA SUCURSAL");
            redirectAttributes.addFlashAttribute("message", "La sucursal ya se encuentra registrada");
            bindingResult.addError(new FieldError("establishmentsDTO", "name",
                    "El establecimiento ya esta registrado"));
        }
        else{
            log.info(">> establishmentsDTO : SE PUEDE REGISTRAR LA SUCURSAL");
            redirectAttributes.addFlashAttribute("message", "Sucursal registrada");

            Establishment establishment = establishmentService.register(establishmentsDTO);
            EstablishmentDetailDTO establishmentDetailDTO = new EstablishmentDetailDTO();

            //log.info("################################################################################################### IDDDDDDDDDDDDDDDDDDDDDD", establishment.getId());
            //establishmentDetailDTO.setEstablishmentId(establishmentsDTO.getClientId());


        }

        if(bindingResult.hasErrors()){
            return "establishments";
        }


        return  "redirect:/register-establishments";
    }



}
