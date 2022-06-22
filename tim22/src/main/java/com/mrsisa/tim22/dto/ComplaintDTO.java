package com.mrsisa.tim22.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComplaintDTO {
    int reservationId;
    String text;
    boolean userShowedUp;
}
