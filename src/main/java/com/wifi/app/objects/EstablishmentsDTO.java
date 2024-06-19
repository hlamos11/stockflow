package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Getter @Setter
public class EstablishmentsDTO {

    //@Size(min = 3, max = 8, message="El código del producto tiene que tener entre 3 y 8 caracteres")
    private Integer id;
    private Integer clientId;
    @NotBlank(message = "Ingrese el nombre")
    @Length(max = 50, message = "El nombre de la sucursal no debe contener mas de 25 caracteres")
    //@Pattern(regexp = "[A-Z0-9^\\s]+", message="El nombre de la sucursal no puede tener caracteres especiales")
    private String nameestablishment;
    private String province;
    private String product;
    private Boolean purpple;
    private Boolean status;
    private String type;
    private Date datesend;
    private Date dateoperative;
    private Date datedown;
    private Integer inventory;
    private String lastmille;
    private Integer countap;


    @Override
    public String toString() {
        return "EstablishmentsDTO{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", nameestablishment='" + nameestablishment + '\'' +
                ", province='" + province + '\'' +
                ", product='" + product + '\'' +
                ", purpple=" + purpple +
                ", status=" + status +
                ", type='" + type + '\'' +
                ", datesend=" + datesend +
                ", dateoperative=" + dateoperative +
                ", datedown=" + datedown +
                ", inventory=" + inventory +
                ", lastmille='" + lastmille + '\'' +
                ", countap=" + countap +
                '}';
    }
}
