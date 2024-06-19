package com.wifi.app.objects;

import com.wifi.app.repository.ValidPassword;

import javax.validation.constraints.NotBlank;

public class ChangepasswordDTO {

    @NotBlank
    private String password;
    //@NotBlank(message = "Nueva Contraseña")
    @ValidPassword
    private String npassword;
    //@NotBlank(message = "Confirmar contraseña")
    private String rpassword;

    public ChangepasswordDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNpassword() {
        return npassword;
    }

    public void setNpassword(String npassword) {
        this.npassword = npassword;
    }

    public String getRpassword() {
        return rpassword;
    }

    public void setRpassword(String rpassword) {
        this.rpassword = rpassword;
    }


    @Override
    public String toString() {
        return "ChangepasswordDTO{" +
                "password='" + password + '\'' +
                ", npassword='" + npassword + '\'' +
                ", rpassword='" + rpassword + '\'' +
                '}';
    }
}
