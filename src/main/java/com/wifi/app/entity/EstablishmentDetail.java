package com.wifi.app.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="establishment_detail")
@Getter @Setter
public class EstablishmentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn
    private Establishment establishment;

    private String  modelap;
    private Integer countap;
    private Integer countpower;
    private String  modelswitch;
    private Integer countswitch;


    public EstablishmentDetail() {
    }
    /*

    public EstablishmentDetail(String modelap, Integer countap, Integer countpower, String modelswitch, Integer countswitch, Establishment establishments) {
        this.modelap = modelap;
        this.countap = countap;
        this.countpower = countpower;
        this.modelswitch = modelswitch;
        this.countswitch = countswitch;
        this.establishments = establishments;
    }

    public EstablishmentDetail(Integer id, String modelap, Integer countap, Integer countpower, String modelswitch, Integer countswitch, Establishment establishments) {
        this.id = id;
        this.modelap = modelap;
        this.countap = countap;
        this.countpower = countpower;
        this.modelswitch = modelswitch;
        this.countswitch = countswitch;
        this.establishments = establishments;
    }

     */


}
