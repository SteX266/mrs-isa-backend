package com.mrsisa.tim22.dto;

import com.mrsisa.tim22.model.AvailabilityPeriod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvailabilityPeriodDTO {
    private String dateFrom;
    private String dateTo;
    private Integer id;

    public AvailabilityPeriodDTO(AvailabilityPeriod a) {
        this.dateFrom = String.valueOf(a.getDateFrom());
        this.dateTo = String.valueOf(a.getDateTo());
    }
}
