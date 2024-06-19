package com.wifi.app.controllers;



import com.wifi.app.objects.SucursalDetail;
import com.wifi.app.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {


    @Autowired
    QueryService queryservice;


    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @GetMapping("/ClientsTop")
    public List<Object[]> getQuery()
    {
        return queryservice.JPQLQueryEstablishmentTopFive();
    }

    @GetMapping("/SucursalDetail")
    public List<SucursalDetail> getQuerySuc()
    {
        return queryservice.JPQLQuery();
    }

}
