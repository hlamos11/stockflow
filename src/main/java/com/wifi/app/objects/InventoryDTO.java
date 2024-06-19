package com.wifi.app.objects;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryDTO {

    private Integer count, countUsed, countEnabled;

    @Override
    public String toString() {
        return "InventoryDTO{" +
                "count=" + count +
                ", countUsed=" + countUsed +
                ", countEnabled=" + countEnabled +
                '}';
    }
}
