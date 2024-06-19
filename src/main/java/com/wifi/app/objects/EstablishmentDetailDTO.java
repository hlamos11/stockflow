package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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

    @Override
    public String toString() {
        return "EstablishmentDetailDTO{" +
                "id=" + id +
                ", establishmentId=" + establishmentId +
                ", modelap='" + modelap + '\'' +
                ", countap=" + countap +
                ", countpower=" + countpower +
                ", modelswitch='" + modelswitch + '\'' +
                ", countswitch=" + countswitch +
                '}';
    }
}
