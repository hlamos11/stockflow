package com.wifi.app.controllers;


import com.wifi.app.entity.Product;
import com.wifi.app.objects.ProductDTO;
import com.wifi.app.service.ProductService;
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
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @GetMapping("/products")
    public String list(Model model) {
        List<Product> products=productService.getProductList();
        model.addAttribute("products", products);
        return "products";
    }

    /*Metodo que valida que los campos no esten en null*/
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor( true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);

    }

    @GetMapping("/register-product")
    public String register(@ModelAttribute ProductDTO productDTO, Model model){
        model.addAttribute( "productDTO", productDTO);
        return "register-product";
    }

    @PostMapping("/register-product")
    public String save(@Validated ProductDTO productDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        //1.1 valida si el cliente existe
        if(productService.ProductExist(productDTO.getName())){
            bindingResult.addError(new FieldError("productDTO", "name",
                    "El producto ya esta registrado"));
        }

        if(bindingResult.hasErrors()){
            return "register-product";
        }

        redirectAttributes.addFlashAttribute("message", "Producto Registrado");
        log.info(">> productDTO : {}", productDTO.toString());
        productService.register(productDTO);
        return  "redirect:/register-product";
    }
}
