package com.wifi.app.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;

@Getter @Setter
@ToString
public class MovementDTO {

    private  Integer Id;
    private String user;
    private Integer materialId;
    private Integer responsibleId;
    private Integer storeId;
    @NotBlank(message = "Debe seleccionar un tipo de movimiento")
    private String typeMovement;

    //@NotBlank(message = "Debe ingresar un sitio")
    private Date dateIn;
    private Date dateOut;
    private Date dateTransfer;
    private Date dateReturn;
    private Integer siteId;

    private Integer step;
    private String comment;

    public MovementDTO() {
    }

}
