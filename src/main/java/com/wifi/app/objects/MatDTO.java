package com.wifi.app.objects;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class MatDTO {

    private String sn,part,model,description;

    public MatDTO(String sn, String part, String model, String description) {
        this.sn = sn;
        this.part = part;
        this.model = model;
        this.description = description;
    }
}
