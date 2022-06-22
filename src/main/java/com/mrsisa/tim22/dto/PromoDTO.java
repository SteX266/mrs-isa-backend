package com.mrsisa.tim22.dto;

import com.mrsisa.tim22.model.Promo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PromoDTO {

    private Integer id;
    private String dateFrom;
    private String dateTo;
    private String description;
    private Integer systemEntityId;
    private int capacity;
    private double price;


    public PromoDTO(Promo p){
        this.id = p.getId();
        this.dateFrom = String.valueOf(p.getDateFrom());
        this.dateTo = String.valueOf(p.getDateTo());
        this.description = p.getDescription();
        this.systemEntityId = p.getSystemEntity().getId();
        this.capacity = p.getCapacity();
        this.price = p.getPrice();
    }

}
