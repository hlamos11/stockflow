package com.wifi.app.controllers;





import com.wifi.app.entity.User;
import com.wifi.app.objects.SucursalDetail;
import com.wifi.app.repository.ClientRepository;
import com.wifi.app.repository.EstablishmentsRepository;
import com.wifi.app.repository.UserRepository;
import com.wifi.app.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigInteger;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static com.wifi.app.res.ConvertDateSql.convertDateString;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private final UserRepository userRepository;

    public static String GLOBAL_USER_NAME = null;

    @Autowired
    QueryService queryservice;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    EstablishmentsRepository establishmentsRepository;


    @GetMapping("/")
    public String index(Authentication authentication){
//        return authentication == null ? "index" : "redirect:/authenticated";
        return authentication == null ? "/login" : "redirect:/authenticated";
    }

    @GetMapping("/authenticated")
    public String authenticated(Authentication authentication,Model model) throws ParseException {

        GLOBAL_USER_NAME = authentication.getName();
        log.info("authenticated.GLOBAL_USER_NAME", GLOBAL_USER_NAME);

        Date date_expired, date_local;

        BigInteger count = (BigInteger) queryservice.JPQLQueryClientsTotal();
        //log.info(">>  count    ********************** : {}",count);
        model.addAttribute("total",count);
        model.addAttribute("clients", clientRepository.findAll());
        //model.addAttribute("establishments", establishmentsRepository.findAll());

        //List<SucursalDetail> detail = queryservice.JPQLQuery();
        //model.addAttribute("detailestablishments", detail);


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date_local = convertDateString(dtf.format(LocalDateTime.now()));

        //log.info("authentication.getName()>>>>>>>>>>>>>>>>>>>>>", authentication.getName());

        Optional<User> user = userRepository.findUserByUsername(authentication.getName());
        date_expired = user.get().getDate_expired();

        if(date_local.after(date_expired) ){
            return "redirect:/changepassword";
        }else {
            return "authenticated";
        }

    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        return authentication == null ? "login" : "redirect:/authenticated";

    }


}
