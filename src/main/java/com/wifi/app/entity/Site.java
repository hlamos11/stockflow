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
@Table(name = "site")
public class Site {

    @Id
    private Integer id;
    private String user;
    @Column(name = "created_at")
    private Timestamp createdAt;
    private String name;
    private String address;
    private boolean enabled;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL)
    private List<Material> materials;

}
