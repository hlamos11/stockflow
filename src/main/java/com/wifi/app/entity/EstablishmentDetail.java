package com.wifi.app.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name="establishment_detail")
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
