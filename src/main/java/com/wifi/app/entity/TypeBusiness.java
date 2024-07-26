package com.wifi.app.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="type_business")
public class TypeBusiness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private boolean enabled;

}
