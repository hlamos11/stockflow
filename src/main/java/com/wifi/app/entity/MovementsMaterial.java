package com.wifi.app.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "movements_material")
public class MovementsMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user")
    private String user;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn
    private Material material;

    @ManyToOne
    @JoinColumn
    private Responsible responsible;

    @ManyToOne
    @JoinColumn
    private Store store;

    @Column(name = "type_movement")
    private String typeMovement;

    @Column(name = "date_in")
    private Date dateIn;

    @Column(name = "date_out")
    private Date dateOut;

    @Column(name = "date_transfer")
    private Date dateTransfer;

    @Column(name = "date_return")
    private Date dateReturn;

    @Column(name = "site_id")
    private String siteId;

    @Column(name = "step")
    private Integer step;

    @Column(name = "comment")
    private String comment;

}
