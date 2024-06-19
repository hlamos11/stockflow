package com.wifi.app.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Getter @Setter
@Table(name = "responsible")
public class Responsible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String user;
    @Column(name = "created_at")
    private Timestamp createdAt;
    private String name;
    private String email;
    private Boolean enabled;

    public Responsible() {
    }
}
