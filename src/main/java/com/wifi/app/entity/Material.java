package com.wifi.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


@Getter @Setter
@Entity
@Table (name = "material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String user;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn
    private Store store;

    @ManyToOne
    @JoinColumn
    private Brand brand;

    private String sn;
    private String part;
    private String model;
    private String description;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
    private Set<MovementsMaterial> movementsMaterial;

    @ManyToOne
    @JoinColumn
    private InventoryMaterial inventoryMaterial;

    private Boolean enabled;

    private String comment;

    @ManyToOne
    @JoinColumn
    private Site site;


    public Material() {
    }
}
