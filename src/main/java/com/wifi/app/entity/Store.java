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
@Table(name = "store")
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

}
