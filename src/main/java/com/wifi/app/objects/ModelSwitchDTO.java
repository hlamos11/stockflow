package com.wifi.app.objects;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ModelSwitchDTO {
    @NotBlank(message = "Debe ingresar el modelo")
    @Length(max = 25, message = "maximo 25 caracteres")
    @Pattern(regexp = "[A-Z0-9^\\s]+", message="El nombre de la sucursal no puede tener caracteres especiales")
    private String modelswitch;
    @NotBlank(message = "Debe ingresar la marca")
    @Length(max = 25, message = "maximo 25 caracteres")
    private String brand;
    private boolean enabled;

    public ModelSwitchDTO() {
    }

    public String getModelswitch() {
        return modelswitch;
    }

    public void setModelswitch(String modelswitch) {
        this.modelswitch = modelswitch;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "ModelSwitchDTO{" +
                "modelswitch='" + modelswitch + '\'' +
                ", brand='" + brand + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
