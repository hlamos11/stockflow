package com.wifi.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="brand")
@Getter @Setter
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String user;
    @Column(name="create_at")
    private Timestamp createAt;
    private String name;
    private Boolean enabled;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Material> materials;

    public Brand() {
    }
}
