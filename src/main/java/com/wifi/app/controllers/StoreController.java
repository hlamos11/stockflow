package com.wifi.app.controllers;

import com.wifi.app.entity.Store;
import com.wifi.app.objects.StoreDTO;
import com.wifi.app.service.StoreService;
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

import static com.wifi.app.controllers.HomeController.GLOBAL_USER_NAME;

@Controller
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private static final Logger log = LoggerFactory.getLogger(StoreController.class);

    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);

    }

    @GetMapping("/store")
    public String getall(Model model){
        List<Store> store = storeService.getStoreList();
        model.addAttribute("dataStore", store);
        return "store";
    }

    @GetMapping("/register-store")
    public String register(@ModelAttribute StoreDTO storeDTO, Model model){

        model.addAttribute("storeDTO", storeDTO);
        return "register-store";
    }

    @PostMapping("/register-store")
    public String save(@Validated StoreDTO storeDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(storeService.storeExists(storeDTO.getName())){
            bindingResult.addError(new FieldError("storeDTO", "nombre",
                    "La Bodega ya esta registrada"));
        }

        if(bindingResult.hasErrors()){
            return "register-store";
        }

        if (GLOBAL_USER_NAME != null ){
            storeDTO.setUser(GLOBAL_USER_NAME);
        }else {
            storeDTO.setUser("nullRegStor");
        }

        redirectAttributes.addFlashAttribute("message", "Bodega Registrada");
        //log.info(">> clientDTO : {}", storeDTO.toString());
        storeService.register(storeDTO);


        return "redirect:/register-store";
    }

    @PostMapping("/delete_store")
    public String delete(@Validated Integer id, RedirectAttributes redirectAttributes) {

        log.info(">> @PostMapping(delete_store) - id : {}", id);


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

        return "redirect:/store";
    }

}
