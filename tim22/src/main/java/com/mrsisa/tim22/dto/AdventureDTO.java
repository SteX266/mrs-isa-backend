package com.mrsisa.tim22.dto;

import com.mrsisa.tim22.model.Adventure;
import com.mrsisa.tim22.model.Amenity;
import com.mrsisa.tim22.model.AvailabilityPeriod;
import com.mrsisa.tim22.model.FishingEquipement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdventureDTO {
    private Integer id;
    private Set<FishingEquipement> fishingEquipement;
    protected String name;
    protected String description;

    private String city;
    private String country;
    private String streetName;
    private int streetNumber;
    protected int capacity;
    protected List<String> photos;
    public List<String> photoStrings;
    protected String rulesOfConduct;
    protected List<Amenity> amenities;
    protected double cancellationFee;
    protected List<AvailabilityPeriodDTO> availabilityPeriod;
    protected double price;
    public AdventureDTO(Adventure v){
        this.id = v.getId();
        this.fishingEquipement=v.getEquipement();
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
    }}