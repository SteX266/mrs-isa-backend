package com.mrsisa.tim22.model;

import com.mrsisa.tim22.dto.PromoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Promo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private LocalDateTime dateFrom;
    @Column
    private LocalDateTime dateTo;
    @Column
    private String description;
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="system_entity_id")
    private SystemEntity systemEntity;
    @Column
    private int capacity;
    @Column
    private double price;
    @Column
    private boolean isTaken;
    public Promo(PromoDTO promoDTO, SystemEntity entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        dateFrom = LocalDateTime.parse(promoDTO.getDateFrom().replace("T", " ").substring(0, 16), formatter);
        dateTo = LocalDateTime.parse(promoDTO.getDateTo().replace("T", " ").substring(0, 16), formatter);
        description = promoDTO.getDescription();
        systemEntity = entity;
        capacity = promoDTO.getCapacity();
        price = promoDTO.getPrice();
        isTaken = false;
    }
}
