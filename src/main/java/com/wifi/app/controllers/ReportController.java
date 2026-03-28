package com.wifi.app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ReportController {

    @GetMapping("/report-generator-management")
    public String getIni (){
        return "report-generator-management";
    }
}
