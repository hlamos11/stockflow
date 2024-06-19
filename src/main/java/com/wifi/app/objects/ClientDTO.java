package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class ClientDTO {

    @NotBlank(message = "Ingrese el nombre")
    @Length(max = 40, message = "El nombre no debe contener mas de 40 caracteres")
    private String name;
    @NotBlank(message = "Ingrese el telefono")
    @Length(max = 10, message = "El numero de telefono no debe contener mas de 10 numeros")
    private String phone;
    @NotBlank(message = "Ingrese la direccion")
    @Length(max = 200, message = "La direccion no debe contener mas de 200 caracteres")
    private String address;
    @Length(max = 200, message = "La nota no debe contener mas de 200 caracteres")
    private String nota;
    private boolean enabled;

    private Integer typeClientId;


    public ClientDTO() {
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", nota='" + nota + '\'' +
                ", enabled=" + enabled +
                ", typeClientId=" + typeClientId +
                '}';
    }
}
