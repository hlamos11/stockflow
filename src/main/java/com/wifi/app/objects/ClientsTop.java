package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class ClientsTop {

    private Integer count;
    private String name;

    public ClientsTop(Integer count, String name) {
        this.count = count;
        this.name = name;
    }

    public ClientsTop(Integer count) {
        this.count = count;
    }


}
