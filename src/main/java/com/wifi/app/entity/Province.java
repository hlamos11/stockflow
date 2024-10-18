package com.wifi.app.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="province")
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String region;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
    private List<MobilGenerator> mobilGeneratorList;

}
