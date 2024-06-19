package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


@Getter @Setter
public class StoreDTO {

    private String user;
    @NotBlank(message = "Debe ingresar el nombre de la bodega")
    @Length(max = 50, message = "El nombre no debe contener mas de 50 caracteres")
    private String name;
    private Boolean enabled;

    public StoreDTO() {
    }

    @Override
    public String toString() {
        return "StoreDTO{" +
                "user='" + user + '\'' +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
