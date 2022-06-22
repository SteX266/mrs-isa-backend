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
public class AvailabilityPeriod {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="date_from", nullable = false)
    private LocalDateTime dateFrom;
    @Column(name="date_to")
    private LocalDateTime dateTo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "systemEntity")
    private SystemEntity systemEntity;

    public AvailabilityPeriod(LocalDateTime dateFrom, LocalDateTime dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
