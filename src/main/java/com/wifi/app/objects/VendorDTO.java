package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter @Setter
@ToString
public class VendorDTO {

    private String user;
    private Timestamp createAt;
    @NotBlank(message = "Ingrese el nombre")
    private String name;
    private Boolean enabled;

    public VendorDTO() {
    }

}
