package com.wifi.app.objects;

public class AuthorityDTO {


    private Integer id;
    private String authority;

    public AuthorityDTO() {
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

    @Override
    public String toString() {
        return "AuthorityDTO{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                '}';
    }
}
