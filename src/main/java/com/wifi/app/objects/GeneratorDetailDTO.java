package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GeneratorDetailDTO {

    private Integer id;
    private String user;
    private Integer hoursWorked;
    private Integer currentLevel;
    private Integer refill;
    private Double estimatedAmount;
    private Integer mobilGeneratorId;

    public GeneratorDetailDTO() {
    }
}
