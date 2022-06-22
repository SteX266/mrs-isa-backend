package com.mrsisa.tim22.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Entity
    public class ReservationReport {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column
        private String text;
        @Column
        private boolean isAnswered;
        @Column
        private boolean automaticPenalty;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="client_id")
        private User client;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="owner_id")
        private User owner;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="reservation")
        private Reservation reservation;


        public ReservationReport(String text, boolean userShowedUp, User u, Reservation reservation) {
            this.text = text;
            this.automaticPenalty = !userShowedUp;
            this.isAnswered = false;
            this.client = u;
            this.owner = reservation.getSystemEntity().getOwner();
            this.reservation = reservation;
        }
    }
