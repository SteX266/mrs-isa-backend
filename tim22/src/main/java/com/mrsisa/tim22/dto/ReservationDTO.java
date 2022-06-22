package com.mrsisa.tim22.dto;

import com.mrsisa.tim22.model.Address;
import com.mrsisa.tim22.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationDTO {
    private int id;
    private String location;
    private String startDate;
    private String endDate;
    private int visitors;
    private double fee;
    private String owner;
    private String client;
    private String status;
    private String entityName;
    private String entityType;
    private double ownerPrice;


    public ReservationDTO(Reservation r) {
        String address = createAddressString(r.getSystemEntity().getAddress());
        String statusString = createStatusString(r.isCanceled(), r.isApproved());
        this.setId(r.getId());
        this.setLocation(address);
        this.setStartDate(String.valueOf(r.getDateFrom()));
        this.setEndDate(String.valueOf(r.getDateTo()));
        this.setVisitors(r.getSystemEntity().getCapacity());
        this.setFee(r.getClientPrice());
        this.setOwner(r.getSystemEntity().getOwner().getName() + " " + r.getSystemEntity().getOwner().getSurname());
        this.setClient(r.getClient().getName() + " " + r.getClient().getSurname());
        this.setStatus(statusString);
        this.setEntityName(r.getSystemEntity().getName());
        this.setEntityType(String.valueOf(r.getSystemEntity().getEntityType()));
        this.setOwnerPrice(r.getOwnerPrice());

    }


    private String createStatusString(boolean canceled, boolean isApproved) {
        if (canceled){
            return "CANCELED";
        }
        else if(isApproved){
            return "APPROVED";
        }
        else{
            return "WAITING";
        }
    }

    private String createAddressString(Address address) {
        String addressString = "";
        addressString += address.getStreetName();
        addressString += " ";
        addressString += String.valueOf(address.getStreetNumber());
        addressString += ", ";
        addressString += address.getCity();
        addressString += ", ";
        addressString += address.getCountry();
        return addressString;
    }
}
