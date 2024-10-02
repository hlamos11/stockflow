package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class MaterialTopFiveByStore {

    private String store;
    private String material;
    private Integer count;

    public MaterialTopFiveByStore() {
    }

    public MaterialTopFiveByStore(String store, String material, Integer count) {
        this.store = store;
        this.material = material;
        this.count = count;
    }
}
