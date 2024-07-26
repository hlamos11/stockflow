package com.wifi.app.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "Capitalizations")
public class Capitalizations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String orden;
    @ManyToOne
    @JoinColumn
    private Clients client;
    private String serialap;
    private String modelswitch;
    private String serialswitch;
    private String modelpoe;
    private String serialpoe;
    private String reserve;
    private Integer month;
    private boolean enabled;
    @Column(name = "created_at")
    private Timestamp createdAt;

}
