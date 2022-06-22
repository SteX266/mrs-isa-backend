package com.mrsisa.tim22.dto;
import com.mrsisa.tim22.model.AccountCancellationRequest;
import com.mrsisa.tim22.model.Reservation;
import com.mrsisa.tim22.model.ReservationReport;
import com.mrsisa.tim22.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ReservationReportDTO {

    private Integer id;

    private String client;

    private String owner;
    private String reservation;

    private String text;







    public ReservationReportDTO(ReservationReport acr){
        this.text = acr.getText();
        this.id = acr.getId();
        this.client = acr.getClient().getUsername();
        this.owner = acr.getOwner().getUsername();
        this.reservation = acr.getReservation().getSystemEntity().getName();
    }



}
