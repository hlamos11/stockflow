package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.sql.Date;

@Getter
@Setter
@ToString
public class GeneratorDetailDTO {

    private  Integer Id;
    private String user;
    private Integer hoursWorked;
    private Integer previousLevel;
    private Integer currentLevel;
    @Min(value = 1, message = "El valor minimo es 1")
    private Integer refill;
    @Min(value = 1, message = "El valor mínimo es 1")
    private Double estimatedAmount;
    private Integer mobilGeneratorId;
    private Integer hourMeter;
    private Date date;

    public GeneratorDetailDTO() {
    }
}
