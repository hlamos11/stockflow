package com.wifi.app.objects;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "TypeBusinessDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
