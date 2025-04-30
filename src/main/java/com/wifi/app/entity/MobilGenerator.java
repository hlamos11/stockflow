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

    @Column(name = "current_level")
    private Integer currentLevel;

    private Integer refill;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="site_id")
    private Site site;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="province_id")
    private Province province;

    @OneToMany(mappedBy = "mobilGenerator", cascade = CascadeType.ALL)
    private List<MobilGeneratorDetail> MobilGeneratorDetail;

}
