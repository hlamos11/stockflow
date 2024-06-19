package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter
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

    @Override
    public String toString() {
        return "LicensePortalDTO{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", date=" + date +
                ", establishmentDetailId=" + establishmentDetailId +
                ", license='" + license + '\'' +
                ", enabled=" + enabled +
                ", dateExpired=" + dateExpired +
                ", establishmentId=" + establishmentId +
                ", orderId=" + orderId +
                '}';
    }
}
