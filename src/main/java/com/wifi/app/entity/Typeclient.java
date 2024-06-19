package com.wifi.app.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table (name = "type_client")
public class Typeclient {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Integer id;
    private String type;
    private String description;
    private Boolean enabled;

    public Typeclient() {
    }
}
