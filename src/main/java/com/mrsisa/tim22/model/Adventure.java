package com.mrsisa.tim22.model;

import com.mrsisa.tim22.dto.AdventureDTO;
import com.mrsisa.tim22.dto.ListingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Adventure extends SystemEntity {

    @ElementCollection(targetClass=FishingEquipement.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="fishing_equipement")
    @Column(name="adventure_equipement")
    private Set<FishingEquipement> equipement;

    public Adventure(AdventureDTO adventure) {
        this.id = adventure.getId();
        this.amenities = adventure.getAmenities();
        this.cancellationFee = adventure.getCancellationFee();
        this.capacity = adventure.getCapacity();
        this.description = adventure.getDescription();
        this.isDeleted = false;
        this.entityType = SystemEntityType.VACATION;
        this.name = adventure.getName();
        this.price = adventure.getPrice();
        this.rulesOfConduct = adventure.getRulesOfConduct();
        // this.photos = adventure.getPhotos();

    }


}
