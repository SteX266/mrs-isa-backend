package com.mrsisa.tim22.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class LoyaltyProgram {
    @Id
    private Integer id;
    @Column
    private int pointsPerReservation;
    @Column
    private int pointsForBusiness;
    @Column
    private int silverLimit;
    @Column
    private int silverDiscount;
    @Column
    private int goldLimit;
    @Column
    private int goldDiscount;
    @Column
    private int platinumLimit;
    @Column
    private int platinumDiscount;

    public int getDiscountByPoints(int points){
        if (points >= platinumLimit){
            return platinumDiscount;
        }
        else if (points >= goldLimit){
            return goldDiscount;
        }
        else if (points >= silverLimit){
            return silverDiscount;
        }
        else{
            return 0;
        }
    }
    public String getTierByPoints(int points){

        if (points >= platinumLimit){
            return "PLATINUM";
        }
        else if (points >= goldLimit){
            return "GOLD";
        }
        else if (points >= silverLimit){
            return "SILVER";
        }
        else{
            return "REGULAR";
        }
    }


}
