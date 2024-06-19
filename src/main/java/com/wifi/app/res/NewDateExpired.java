package com.wifi.app.res;

import com.wifi.app.controllers.AccountController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

import static com.wifi.app.res.ConvertDateSql.convert;

public class NewDateExpired {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    public static java.sql.Date NewDate(String typeuser){

        log.info(">> ******************************************************* typeuser : {}", typeuser);

        int count=0;

        if(typeuser.equals("admin")){
            count= 90;
        }else if(typeuser.equals("readonly")){
            count=90;
        }

        log.info(">> ******************************************************* count : {}", count);

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, count);
        dt = c.getTime();

        java.sql.Date sqlDate = convert(dt);

        return sqlDate;
    }
}
