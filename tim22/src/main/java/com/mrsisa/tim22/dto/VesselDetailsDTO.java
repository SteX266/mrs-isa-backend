package com.mrsisa.tim22.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VesselDetailsDTO {
    private int maxSpeed;
    private int engineNumber;
    private int enginePower;
    private Integer serviceID;
}
