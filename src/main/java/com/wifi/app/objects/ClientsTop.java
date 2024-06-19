package com.wifi.app.objects;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
