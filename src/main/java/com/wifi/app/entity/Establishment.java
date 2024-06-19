package com.wifi.app.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="establishment")
@Getter @Setter
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nameestablishment;
    private String province;
    private String product;
    private Boolean purpple;
    private Boolean status;
    private String type;
    private Date datesend;
    private Date dateoperative;
    private Date datedown;
    private Integer inventory;
    private String lastmille;
    @Column(name = "created_at")
    private Timestamp createdAt;
    private Integer countap;

    //esta variable toma el valor como client_id para registrar EstablishmentsDTO
    //tambien realiza un join con clients para mostrar los detalles cliente-sucursales
    @ManyToOne
    @JoinColumn
    private Clients client;

    @OneToMany(mappedBy = "establishment", cascade = CascadeType.ALL)
    private Set<LicenseMeraki> licenseMeraki;

    @OneToMany(mappedBy = "establishment", cascade = CascadeType.ALL)
    private Set<LicensePortal> licensePortals;

    @OneToMany(mappedBy = "establishment", cascade = CascadeType.ALL)
    private Set<EstablishmentDetail> establishmentDetail;

    public Establishment() {
    }

    @Override
    public String toString() {
        return "Establishment{" +
                "id=" + id +
                ", nameestablishment='" + nameestablishment + '\'' +
                ", province='" + province + '\'' +
                ", product='" + product + '\'' +
                ", purpple=" + purpple +
                ", status=" + status +
                ", type='" + type + '\'' +
                ", datesend=" + datesend +
                ", dateoperative=" + dateoperative +
                ", datedown=" + datedown +
                ", inventory=" + inventory +
                ", lastmille='" + lastmille + '\'' +
                ", createdAt=" + createdAt +
                ", countap=" + countap +
                ", client=" + client +
                ", licenseMeraki=" + licenseMeraki +
                ", licensePortals=" + licensePortals +
                ", establishmentDetail=" + establishmentDetail +
                '}';
    }
}
