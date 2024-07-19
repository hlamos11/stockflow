package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class EstablishmentDetailDTO {

    private Integer id;
    private Integer establishmentId;
    private String  modelap;
    private Integer countap;
    private Integer countpower;
    private String  modelswitch;
    private Integer countswitch;

    public EstablishmentDetailDTO() {
    }

}
