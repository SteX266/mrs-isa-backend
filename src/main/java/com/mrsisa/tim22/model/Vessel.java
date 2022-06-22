package com.mrsisa.tim22.model;

import com.mrsisa.tim22.dto.VesselDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Vessel extends SystemEntity {
    @Column
    @Enumerated(EnumType.STRING)
    private VesselType vesselType;
    @Column
    private int engineNumber;
    @Column
    private int enginePower;
    @Column
    private int maxSpeed;
    @ElementCollection(targetClass=VesselEquipement.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="vessel_equipement")
    @Column(name="ship_vessel_equipement")
    private List<VesselEquipement> vesselEquipement;
    @ElementCollection(targetClass=FishingEquipement.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="fishing_equipement")
    @Column(name="ship_fishing_equipement")
    private List<FishingEquipement> fishingEquipement;

    public Vessel(VesselDTO vesselDTO) {

        vesselType = vesselDTO.getVesselType();
        engineNumber = vesselDTO.getEngineNumber();
        enginePower = vesselDTO.getEnginePower();
        maxSpeed = vesselDTO.getMaxSpeed();
        vesselEquipement = vesselDTO.getVesselEquipement();
        fishingEquipement = vesselDTO.getFishingEquipement();
        this.amenities = vesselDTO.getAmenities();
        this.cancellationFee = vesselDTO.getCancellationFee();
        this.price = vesselDTO.getPrice();
        this.capacity = vesselDTO.getCapacity();
        this.description = vesselDTO.getDescription();
        this.entityType = SystemEntityType.VESSEL;
        this.name = vesselDTO.getName();
        // photos treba da se uradi isto
        this.isDeleted = false;
        this.rulesOfConduct = vesselDTO.getRulesOfConduct();
    }

}
