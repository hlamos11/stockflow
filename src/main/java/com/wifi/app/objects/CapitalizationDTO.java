package com.wifi.app.objects;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CapitalizationDTO {

    private Integer id;
    private String orden;
    private Integer clientId;
    private String serialap;
    private String modelswitch;
    private String serialswitch;
    private String modelpoe;
    private String serialpoe;
    private String reserve;
    private Integer month;
    private boolean enabled;

    public CapitalizationDTO() {
    }

    @Override
    public String toString() {
        return "CapitalizationDTO{" +
                "orden='" + orden + '\'' +
                ", clientId=" + clientId +
                ", serialap='" + serialap + '\'' +
                ", modelswitch='" + modelswitch + '\'' +
                ", serialswitch='" + serialswitch + '\'' +
                ", modelpoe='" + modelpoe + '\'' +
                ", serialpoe='" + serialpoe + '\'' +
                ", reserve='" + reserve + '\'' +
                ", month=" + month +
                ", enabled=" + enabled +
                '}';
    }
}
