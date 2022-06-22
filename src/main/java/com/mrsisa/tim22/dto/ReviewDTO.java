package com.mrsisa.tim22.dto;

import com.mrsisa.tim22.model.Review;
import com.mrsisa.tim22.model.SystemEntity;
import com.mrsisa.tim22.model.User;
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

public class ReviewDTO {

    private int id;
    private String client;
    private String owner;
    private String systemEntity;
    private int score;
    private String text;









    public ReviewDTO(Review r){
        this.id = r.getId();
        this.score = r.getScore();
        this.text = r.getText();
        this.client = r.getClient().getUsername();
        this.systemEntity = r.getSystemEntity().getName();
        this.owner= r.getSystemEntity().getOwner().getUsername();

    }

}

