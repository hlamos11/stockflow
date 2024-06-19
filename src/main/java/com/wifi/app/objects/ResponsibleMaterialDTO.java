package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class ResponsibleMaterialDTO {

    private String user;
    @NotBlank(message = "Debe ingresar el nombre del responsable")
    @Length(max = 50, message = "El nombre no debe contener mas de 50 caracteres")
    private String name;
    @NotBlank(message = "Debe ingresar el Email")
    @Email
    private String email;
    private Boolean enabled;

    public ResponsibleMaterialDTO() {
    }

    @Override
    public String toString() {
        return "ResponsibleMaterialDTO{" +
                "user='" + user + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
