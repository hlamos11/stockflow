package com.wifi.app.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "licenses_portal")
public class LicensePortal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String user;
    private Date date;
    @Column(name = "establishment_detail_id")
    private  Integer establishmentDetailId;
    private String license;
    private boolean enabled;
    @Column(name = "date_expired")
    private Date dateExpired;

    @ManyToOne
    @JoinColumn
    private Establishment establishment;

    @ManyToOne
    @JoinColumn
    private OrderLicensesPortal order;


}
