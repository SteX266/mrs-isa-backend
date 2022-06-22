package com.mrsisa.tim22.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationsReportDTO {
    private String name;
    private int Attendance;


    public void increaseAmount(){
        this.Attendance += 1;
    }
    public void increaseAmount(int i){
        this.Attendance += i;
    }
}
