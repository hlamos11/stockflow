package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ResultQueryDTO {

    private Integer count;
    private Integer id;
    private String description;
    private String model;


    public ResultQueryDTO(Integer count, Integer id, String description, String model) {
        this.count = count;
        this.id = id;
        this.description = description;
        this.model = model;
    }
}
