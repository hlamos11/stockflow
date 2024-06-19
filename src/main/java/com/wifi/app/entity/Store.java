package com.wifi.app.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "store")
@Getter @Setter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String user;
    private String name;
    @Column(name = "created_at")
    private Timestamp createdAt;
    private Boolean enabled;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Material> materials;

    public Store() {
    }

}
