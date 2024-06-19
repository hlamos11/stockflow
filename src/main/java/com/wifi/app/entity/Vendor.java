package com.wifi.app.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "vendor")
@Getter @Setter
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String user;

    @Column(name = "create_at")
    private Timestamp createAt;

    private String name;

    private Boolean enabled;

    public Vendor() {
    }
}
