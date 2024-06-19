package com.wifi.app.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter @Setter
@Entity
@Table(name="model_ap")
public class ModelAp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String modelap;
    private String brand;
    private boolean enabled;
    @Column(name = "created_at")
    private Timestamp createdAt;


    public ModelAp() {
    }


}
