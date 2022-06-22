package com.mrsisa.tim22.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationRequestDTO {
    private String dateFrom;
    private String dateTo;
    private int entityId;
    private String username;

}
