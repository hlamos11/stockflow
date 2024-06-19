package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter @Setter
public class VendorDTO {

    private String user;
    private Timestamp createAt;
    @NotBlank(message = "Ingrese el nombre")
    private String name;
    private Boolean enabled;

    public VendorDTO() {
    }

    @Override
    public String toString() {
        return "VendorDTO{" +
                ", user='" + user + '\'' +
                ", createAt=" + createAt +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
