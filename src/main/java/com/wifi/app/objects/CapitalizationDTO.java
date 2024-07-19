package com.wifi.app.objects;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
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
}
