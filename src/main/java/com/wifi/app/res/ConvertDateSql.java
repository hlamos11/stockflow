package com.wifi.app.res;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ConvertDateSql {

    private static final Logger log = LoggerFactory.getLogger(ConvertDateSql.class);

    public static java.sql.Date convert(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    public static java.sql.Date convertDateString(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse(strDate);
        java.sql.Date sqlDate = new Date(date.getTime());
        return sqlDate;
    }


}
