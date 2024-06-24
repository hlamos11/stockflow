package com.wifi.app.controllers;



import com.wifi.app.entity.Clients;
import com.wifi.app.entity.Typeclient;
import com.wifi.app.objects.ClientDTO;
import com.wifi.app.service.ClientService;
import com.wifi.app.service.EstablishmentService;
import com.wifi.app.service.QueryService;
import com.wifi.app.service.TypeClientService;
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
public class ClientController {

    @Autowired
    QueryService queryservice;

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);
    private final ClientService clientService;
    private final TypeClientService typeClientService;
    private final EstablishmentService establishmentService;

    @GetMapping("/clients")
    public String list(Model model) {
        List<Clients> clients = clientService.getClientList();
        model.addAttribute("client", clients);
        return "clients";
    }

    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);

    }

    @GetMapping("/register-client")
    public String register(@ModelAttribute ClientDTO clientDTO, Model model){

        List<Typeclient> typeclientList = typeClientService.getListTypeClient();

        model.addAttribute( "typeClient", typeclientList);
        model.addAttribute( "clientDTO", clientDTO);
        return "register-client";
    }

    @PostMapping("/register-client")
    public String save(@Validated ClientDTO clientDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        //log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>", clientDTO.getName());
        //valida si el cliente existe
        if(clientService.clientExists(clientDTO.getName())){
            bindingResult.addError(new FieldError("clientDTO", "name",
                    "El cliente ya esta registrado"));
        }

        if(bindingResult.hasErrors()){
            return "register-client";
        }

        redirectAttributes.addFlashAttribute("message", "Cliente Registrado");
        log.info(">> clientDTO : {}", clientDTO.toString());
        clientService.register(clientDTO);
        return  "redirect:/register-client";
    }

    @PostMapping("/delete_client")
    public String delete(@Validated Integer id, RedirectAttributes redirectAttributes) {

        //log.info(">> @PostMapping(delete_client) - id : {}", id);


        BigInteger count = (BigInteger) queryservice.JPQLQueryEstablishmentByClientId(id);

        /*
        log.info(">> delete_client count", count);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> count.equals(BigInteger.ZERO) ", count.equals(BigInteger.ZERO));
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> BigInteger.ZERO", BigInteger.ZERO);


        if(count == null){
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> count == null");
        }

        if(count == null || count.equals(BigInteger.ZERO)){
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> count == null || count.equals(BigInteger.ZERO) ");
        }
        */

        //valida si el cliente tiene sucursales creadas
        if(count.equals(BigInteger.ZERO)) {
            //log.info(">> @PostMapping(delete_client) - Si se puede eliminar el id : {}", id);
            clientService.deleteClientById(id);
            redirectAttributes.addFlashAttribute("message_succefull", "Cliente Eliminado");

        }else {
            //log.info(">> @PostMapping(delete_client) - No se puede eliminar el id : {}", id);
            redirectAttributes.addFlashAttribute("message", "No se puede eliminar un cliente si tiene sucursales activas");
        }

        return "redirect:/clients";
    }
}
