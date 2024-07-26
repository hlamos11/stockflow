package com.wifi.app.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name="model_switch")
public class ModelSwitch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String modelswitch;
    private String brand;
    private boolean enabled;
    @Column(name = "created_at")
    private Timestamp createdAt;

}
