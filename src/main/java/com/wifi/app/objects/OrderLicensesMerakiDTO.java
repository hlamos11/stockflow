package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;


import java.sql.Date;

@Getter @Setter
public class OrderLicensesMerakiDTO {

    private Integer ponumber;

    private String facturenumber;

    private Integer countlicenses;

    private String ordernumber;

    private  Date datepurchase;

    private Date dateexpiration;

    private  Integer countlicensesavailable;

    private  Integer countlicensesused;

    private  Integer countlicensesexpired;

    public OrderLicensesMerakiDTO() {
    }

    @Override
    public String toString() {
        return "OrderLicensesMerakiDTO{" +
                "ponumber=" + ponumber +
                ", facturenumber='" + facturenumber + '\'' +
                ", countlicenses=" + countlicenses +
                ", ordernumber='" + ordernumber + '\'' +
                ", datepurchase=" + datepurchase +
                ", dateexpiration=" + dateexpiration +
                ", countlicensesavailable=" + countlicensesavailable +
                ", countlicensesused=" + countlicensesused +
                ", countlicensesexpired=" + countlicensesexpired +
                '}';
    }
}
