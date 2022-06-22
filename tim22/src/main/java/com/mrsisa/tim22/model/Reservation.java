package com.mrsisa.tim22.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private LocalDateTime dateFrom;
    @Column
    private LocalDateTime dateTo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="system_entity_id")
    private SystemEntity systemEntity;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private User client;
    @Column
    private boolean isApproved;
    @Column
    private boolean isCanceled;

    @Column
    private double clientPrice;

    @Column
    private double ownerPrice;

    public Reservation(Promo p, User u, double clientPrice, double ownerPrice){
        this.dateFrom = p.getDateFrom();
        this.dateTo = p.getDateTo();
        this.systemEntity = p.getSystemEntity();
        this.client = u;
        this.isApproved = true;
        this.isCanceled = false;
    }

    public Reservation(SystemEntity entity, LocalDateTime dateFrom, LocalDateTime dateTo, User u,double clientPrice,double ownerPrice){
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.systemEntity = entity;
        this.client = u;
        this.isApproved = true;
        this.isCanceled = false;
        this.clientPrice = clientPrice;
        this.ownerPrice = ownerPrice;

    }


}
