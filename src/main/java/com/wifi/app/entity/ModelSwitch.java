package com.wifi.app.entity;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="model_switch")
public class ModelSwitch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String modelswitch;
    private String brand;
    private boolean enabled;
    @Column(name = "created_at")
    private Timestamp createdAt;

    public ModelSwitch() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
