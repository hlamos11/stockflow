package com.wifi.app.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "order_licenses_portal")
public class OrderLicensesPortal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer ponumber;

    private String facturenumber;

    private Integer countlicenses;

    private String ordernumber;

    private  Date datepurchase;

    private Date dateexpiration;

    private  Integer countlicensesavailable;

    private  Integer countlicensesused;

    private  Integer countlicensesexpired;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<LicensePortal> order;
}
