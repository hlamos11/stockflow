package com.wifi.app.controllers;


import com.wifi.app.entity.Store;
import com.wifi.app.entity.Typeclient;
import com.wifi.app.objects.*;
import com.wifi.app.service.GeneratorService;
import com.wifi.app.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequiredArgsConstructor
public class GeneratorController {

    private static final Logger log = LoggerFactory.getLogger(GeneratorController.class);

    private final GeneratorService generatorService;

    @Autowired
    QueryService queryservice;

    @GetMapping("/generator-management")
    public String getInit(Model model){


        model.addAttribute("dataMobilGenerator",generatorService.getListMobilGenerator());
        return "generator-management";
    }


    @RequestMapping(value="/searchGeneratorHistById", params = "id", method = GET)
    @ResponseBody
    public String searchGeneratorHistById(@RequestParam("id") int id){


        System.out.println("id: " + id);
        List<GeneratorHistDTO> list = queryservice.JPQLQueryGeneratorHist(id);
        System.out.println("searchGeneratorHistById: " + list);

        JSONArray jsonArrayGeneratorHist = new JSONArray();

        JSONObject jsonObject = new JSONObject ();

        jsonArrayGeneratorHist.put(list);

        jsonObject.put("ListGeneratorHist", jsonArrayGeneratorHist);

        log.info(">> hlamos : {}",jsonObject.toString());


        return jsonObject.toString();

    }

    @GetMapping("/register-generator-detail")
    public String get(@ModelAttribute GeneratorDetailDTO generatorDetailDTO, Model model){


        return "register-generator-detail";
    }

    @PostMapping("/register-generator-detail")
    public String register(@Validated GeneratorDetailDTO generatorDetailDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){


        return "register-generator-detail";
    }
}
