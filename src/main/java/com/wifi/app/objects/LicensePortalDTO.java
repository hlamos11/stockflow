package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter @Setter
@ToString
public class LicensePortalDTO {

    private Integer id;
    private String user;
    private Date date;
    private  Integer establishmentDetailId;
    private String license;
    private boolean enabled;
    private Date dateExpired;
    private Integer establishmentId;
    private Integer orderId;

    public LicensePortalDTO() {
    }

}
