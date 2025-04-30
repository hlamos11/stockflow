package com.wifi.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name="mobil_generator_detail")
public class MobilGeneratorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String user;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "hours_worked")
    private Integer hoursWorked;

    @Column(name = "previous_level")
    private Integer previousLevel;

    @Column(name = "current_level")
    private Integer currentLevel;

    private Integer refill;

    @Column(name = "estimated_amount")
    private Double estimatedAmount;

    @ManyToOne
    @JoinColumn
    private MobilGenerator mobilGenerator;

    @Column(name = "hour_meter")
    private Integer hourMeter;

    @Column(name = "date")
    private Date date;



}
