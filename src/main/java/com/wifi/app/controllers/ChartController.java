package com.wifi.app.controllers;


import com.wifi.app.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.math.BigInteger;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class ChartController {

    private static final Logger log = LoggerFactory.getLogger(ChartController.class);

    @Autowired
    QueryService queryservice;

    @GetMapping("/charts")
    public String charts(Model model){

        return "charts";
    }


    //@RequestMapping("/chartswifi") -- Comente esta linea tengo que recordar bien como funciona, puse charts mientras
    @RequestMapping("/charts")
    @ResponseBody
    public String getData(){

        List<Object[]> list = queryservice.JPQLQueryEstablishmentTopFive();
        List<Object[]> listM = queryservice.JPQLQueryInstallationsForMonth();

        JSONArray jsonArrayCount = new JSONArray();
        JSONArray jsonArrayName = new JSONArray();

        JSONArray jsonArrayCountm = new JSONArray();
        JSONArray jsonArrayNamem = new JSONArray();

        JSONObject jsonObject = new JSONObject ();

        list.stream().forEach((data) -> {
            Long count = ((BigInteger) data[0]).longValue();
            String name = (String) data[1];

            jsonArrayCount.put(count);
            jsonArrayName.put(name);

        });

        listM.stream().forEach((data) -> {
            Long count = ((BigInteger) data[0]).longValue();
            Date date = (Date) data[1];
            DateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
            String  label = fecha.format(date);

            jsonArrayCountm.put(count);
            jsonArrayNamem.put(label);

        });

        jsonObject.put("count", jsonArrayCount);
        jsonObject.put("name", jsonArrayName);

        jsonObject.put("countm", jsonArrayCountm);
        jsonObject.put("namem", jsonArrayNamem);

//        log.info(">> jsonObject.toString()********************** : {}",jsonObject.toString());


        return jsonObject.toString();

    }



}
