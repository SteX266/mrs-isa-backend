package com.mrsisa.tim22.dto;
import com.mrsisa.tim22.model.*;
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


    public ReservationReportDTO(ReservationReport acr) {
        this.text = acr.getText();
        this.id = acr.getId();
        this.client = acr.getClient().getUsername();
        this.owner = acr.getOwner().getUsername();
        this.reservation = acr.getReservation().getSystemEntity().getName();
    }

    public ReservationReportDTO(Complaint acr) {
        this.text = acr.getText();
        this.id = acr.getId();
        this.client = acr.getSender().getUsername();
        this.owner = acr.getSystemEntity().getOwner().getUsername();
        this.reservation = acr.getSystemEntity().getName();
    }
}