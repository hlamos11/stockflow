package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@ToString
public class BrandDTO {

    private String user;
    @NotBlank(message = "Debe ingresar el nombre del fabricante")
    @Length(max = 50, message = "El nombre no debe contener mas de 50 caracteres")
    private String name;
    private Boolean enabled;

    public BrandDTO() {
    }

}
