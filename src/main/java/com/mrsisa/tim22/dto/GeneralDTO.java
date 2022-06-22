package com.mrsisa.tim22.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeneralDTO {
    private String name;
    private String description;
    private String rulesOfConduct;
    private double price;
    private double cancellationFee;
    private int capacity;
    private Integer serviceID;
}
