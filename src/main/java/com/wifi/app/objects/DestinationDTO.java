package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter @Setter
@ToString
public class DestinationDTO {

    private String user;
    private Timestamp create_at;
    @NotBlank(message = "Debe ingresar el nombre del destino")
    @Length(max = 50, message = "El nombre no debe contener mas de 40 caracteres")
    private String name;
    private Boolean enabled;

    public DestinationDTO() {
    }

}
