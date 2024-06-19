package com.wifi.app.objects;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class ModelApDTO {

    @NotBlank(message = "Debe ingresar el modelo")
    @Length(max = 25, message = "maximo 25 caracteres")
    @Pattern(regexp = "[A-Z0-9^\\s]+", message="El modelo no puede tener caracteres especiales")
    private String modelap;
    @NotBlank(message = "Debe ingresar la marca")
    @Length(max = 25, message = "maximo 25 caracteres")
    @Pattern(regexp = "[A-Z0-9^\\s]+", message="La marca no puede tener caracteres especiales")
    private String brand;
    private boolean enabled;


    public ModelApDTO() {
    }

    @Override
    public String toString() {
        return "ModelApDTO{" +
                ", modelap='" + modelap + '\'' +
                ", brand='" + brand + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
