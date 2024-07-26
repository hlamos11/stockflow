package com.wifi.app.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table (name = "type_client")
public class Typeclient {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private  Integer id;
    private String type;
    private String description;
    private Boolean enabled;

}
