package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter @Setter
@ToString
public class MaterialDTO {

    private Integer id;
    private String user;
    private Integer storeId;
    private Integer brandId;
    private String sn;
    private String part;
    private String model;
    @NotBlank(message = "Debe ingresar la descricion")
    @Length(max = 150, message = "La descripcion no debe contener mas de 150 caracteres")
    private String description;

    private Integer inventoryMaterialId;

    private Boolean enabled;

    @Length(max = 50, message = "El comentario no debe contener mas de 145 caracteres")
    private String comment;

    private Integer siteId;


    public MaterialDTO() {
    }

}
