package com.mrsisa.tim22.dto;

import com.mrsisa.tim22.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VesselDTO {
    private Integer id;
    private VesselType vesselType;
    private int engineNumber;
    private int enginePower;
    private int maxSpeed;
    private List<VesselEquipement> vesselEquipement;
    private List<FishingEquipement> fishingEquipement;
    protected String name;
    protected String description;
    protected int capacity;
    private String city;
    private String country;
    private String streetName;
    private int streetNumber;
    protected List<String> photos;
    public List<String> photoStrings;
    protected String rulesOfConduct;
    protected List<Amenity> amenities;
    protected double cancellationFee;
    protected List<AvailabilityPeriodDTO> availabilityPeriod;
    protected double price;

    public VesselDTO(Vessel v){
        this.id = v.getId();
        this.vesselType = v.getVesselType();
        this.enginePower=v.getEnginePower();
        this.engineNumber = v.getEngineNumber();
        this.maxSpeed =v.getMaxSpeed();
        this.vesselEquipement = v.getVesselEquipement();
        this.fishingEquipement=v.getFishingEquipement();
        this.name = v.getName();
        this.description=v.getDescription();
        this.city = v.getAddress().getCity();
        this.country = v.getAddress().getCountry();
        this.streetName = v.getAddress().getStreetName();
        this.streetNumber = v.getAddress().getStreetNumber();
        this.capacity=v.getCapacity();
        this.photos=v.getPhotos();
        this.rulesOfConduct=v.getRulesOfConduct();
        this.amenities=v.getAmenities();
        this.cancellationFee =v.getCancellationFee();
        this.availabilityPeriod= new ArrayList<>();
        for (AvailabilityPeriod ap : v.getAvailabilityPeriod()){
            this.availabilityPeriod.add(new AvailabilityPeriodDTO(ap));
        }
        this.price=v.getPrice();
    }


}

