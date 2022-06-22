package com.mrsisa.tim22.model;

import com.mrsisa.tim22.dto.ListingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class Vacation extends SystemEntity {
    public Vacation(ListingDTO listingDTO) {
        this.id = listingDTO.getId();
        this.amenities = listingDTO.getAmenities();
        this.cancellationFee = listingDTO.getCancellationFee();
        this.capacity = listingDTO.getCapacity();
        this.description = listingDTO.getDescription();
        this.isDeleted = false;
        this.entityType = SystemEntityType.VACATION;
        this.name = listingDTO.getName();
        this.price = listingDTO.getPrice();
        this.rulesOfConduct = listingDTO.getRulesOfConduct();
        // this.photos = listingDTO.getPhotos();

    }

}
