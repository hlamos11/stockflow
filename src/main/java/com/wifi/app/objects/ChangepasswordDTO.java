package com.wifi.app.objects;

import com.wifi.app.repository.ValidPassword;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class ChangepasswordDTO {

    @NotBlank
    private String password;
    //@NotBlank(message = "Nueva Contraseña")
    @ValidPassword
    private String npassword;
    //@NotBlank(message = "Confirmar contraseña")
    private String rpassword;

    public ChangepasswordDTO() {
    }
}
