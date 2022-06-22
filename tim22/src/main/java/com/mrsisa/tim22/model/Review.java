package com.mrsisa.tim22.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer score;
    @Column
    private String text;
    @Column
    private boolean isApproved;
    @Column
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="sender_id")
    private User client;
    @ManyToOne
    @JoinColumn(name="system_entity_id")
    private SystemEntity systemEntity;

    public Review(int score, String text, User client, SystemEntity systemEntity){
        this.score = score;
        this.text = text;
        this.client = client;
        this.systemEntity = systemEntity;
        this.date = LocalDate.now();

    }

}
