package com.wifi.app.entity;


import javax.persistence.*;

@Entity
@Table(name="authority")
public class Authority {

    @Id
    private Integer id;

    private String authority;

    public Authority(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
