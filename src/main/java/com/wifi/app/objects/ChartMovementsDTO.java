package com.wifi.app.objects;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ChartMovementsDTO {

    private Integer val;
    private String color;
    private String name;

    public ChartMovementsDTO(Integer val, String color, String name) {
        this.val = val;
        this.color = color;
        this.name = name;
    }
}
