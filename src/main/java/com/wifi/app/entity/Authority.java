package com.wifi.app.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="authority")
public class Authority {

    @Id
    private Integer id;

    private String authority;

}
