package com.wifi.app.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="mobil_generator")
public class MobilGenerator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String user;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "tank_level")
    private Integer tankLevel;

    @ManyToOne
    @JoinColumn
    private Site site;

    @ManyToOne
    @JoinColumn
    private Province province;

    @OneToMany(mappedBy = "mobilGenerator", cascade = CascadeType.ALL)
    private List<MobilGeneratorDetail> MobilGeneratorDetail;
}
