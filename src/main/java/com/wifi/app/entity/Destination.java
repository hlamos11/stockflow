package com.wifi.app.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "destination")
@Getter @Setter
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String user;

    @Column(name = "create_at")
    private Timestamp createAt;

    private String name;

    private Boolean enabled;

    public Destination() {
    }
}
