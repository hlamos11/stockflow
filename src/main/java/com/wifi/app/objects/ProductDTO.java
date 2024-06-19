package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class ProductDTO {

    @NotBlank(message = "Debe ingresar el nombre")
    @Length(max = 25, message = "maximo 25 caracteres")
    @Pattern(regexp = "[A-Z0-9^\\s]+", message="El nombre del producto no puede tener caracteres especiales")
    private String name;
    @Length(max = 100, message = "maximo 25 caracteres")
    private String description;
    private boolean enabled;

    public ProductDTO() {
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
