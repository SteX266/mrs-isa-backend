package com.mrsisa.tim22.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PeriodsDTO {
    private List<AvailabilityPeriodDTO> availabilityPeriodDTOS;
    private Integer serviceID;
}
