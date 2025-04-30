package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class GeneratorDTO {

    private String user;
    private Integer tankLevel;
    private Integer currentLevel;
    private Integer refill;
    private Integer siteId;
    private Integer provinceId;
}
