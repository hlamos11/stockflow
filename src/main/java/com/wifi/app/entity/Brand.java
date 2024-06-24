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
@Table(name="brand")

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

}
