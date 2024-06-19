package com.wifi.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "Clients")
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private String nota;
    private boolean enabled;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "type_client_id")
    private Integer typeClientId;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Establishment> establishments;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Capitalizations> capitalizations;

    public Clients() {
    }

}
