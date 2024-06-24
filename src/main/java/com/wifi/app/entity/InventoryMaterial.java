package com.wifi.app.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "inventory_material")
public class InventoryMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String user;
    private Boolean enabled;
    private Timestamp createdAt;
    private String name;
    private Integer year;
    private Integer count;

    @Column(name="count_used")
    private Integer countUsed;

    @Column(name="count_enabled")
    private Integer countEnabled;

    @OneToMany(mappedBy = "inventoryMaterial", cascade = CascadeType.ALL)
    private List<Material> materials;

}
