package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@ToString
@Getter @Setter
public class GeneratorRegisterDTO {

    private String user;
    private Integer tankLevel;
    private Integer currentLevel;
    private Integer refill;
    private Integer siteId;
    private Integer provinceId;
    private Integer hoursWorked;

    private Integer hourMeter;
    private Double estimatedAmount;
    private Date date;
}
