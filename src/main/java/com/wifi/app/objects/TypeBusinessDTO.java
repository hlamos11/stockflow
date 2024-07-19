package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @Setter
@ToString
public class TypeBusinessDTO {

    @NotBlank(message = "Debe ingresar el tipo de negocio")
    @Length(max = 25, message = "maximo 25 caracteres")
    @Pattern(regexp = "[A-Z0-9^\\s]+", message="El nombre del tipo de negocio no puede tener caracteres especiales")
    private String name;
    @Length(max = 100, message = "maximo 100 caracteres")
    private String description;
    private boolean enabled;

    public TypeBusinessDTO() {
    }

}
