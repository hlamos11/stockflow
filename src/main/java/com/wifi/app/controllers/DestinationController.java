package com.wifi.app.controllers;

import com.wifi.app.entity.Destination;
import com.wifi.app.objects.DestinationDTO;
import com.wifi.app.service.DestinationService;
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
public class DestinationController {

    private final DestinationService destinationService;
    private static final Logger log = LoggerFactory.getLogger(DestinationController.class);


    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);

    }

    @GetMapping("/destination")
    public String getDestination(Model model){
        List<Destination> list = destinationService.getFindDestinationAll();
        model.addAttribute("dataDestination", list);
        return "destination";
    }

    @GetMapping("/register-destination")
    public String regDestination(@ModelAttribute  DestinationDTO destinationDTO, Model model){

        model.addAttribute("destinationDTO", destinationDTO);

        return "register-destination";
    }

    @PostMapping("/register-destination")
    public String save(@Validated DestinationDTO destinationDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        destinationDTO.setUser("CAMBIA");

        if(destinationService.destinationExists(destinationDTO.getName())){
            bindingResult.addError(new FieldError("destinationDTO", "name",
                    "El destino ya esta registrado"));

            redirectAttributes.addFlashAttribute("message", "El Destino ya esta registrado");
        }

        if(bindingResult.hasErrors()){
            return "register-destination";
        }

        redirectAttributes.addFlashAttribute("message", "Destino Registrado");
        //log.info(">> destinationDTO : {}", destinationDTO.toString());
        destinationService.register(destinationDTO);

        return "redirect:/register-destination";
    }

    @PostMapping("/delete_destination")
    public String delete(@Validated Integer id, RedirectAttributes redirectAttributes) {

        log.info(">> @PostMapping(delete_destination) - id : {}", id);


        //BigInteger count = (BigInteger) queryservice.JPQLQueryEstablishmentByClientId(id);


        //valida si el cliente tiene sucursales creadas
       /* if(count.equals(BigInteger.ZERO)) {
            //log.info(">> @PostMapping(delete_client) - Si se puede eliminar el id : {}", id);
            clientService.deleteClientById(id);
            redirectAttributes.addFlashAttribute("message_succefull", "Cliente Eliminado");

        }else {
            //log.info(">> @PostMapping(delete_client) - No se puede eliminar el id : {}", id);
            redirectAttributes.addFlashAttribute("message", "No se puede eliminar un cliente si tiene sucursales activas");
        }

        */

        return "redirect:/destination";
    }
}
