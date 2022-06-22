package com.mrsisa.tim22.dto;

import com.mrsisa.tim22.model.Address;
import com.mrsisa.tim22.model.Amenity;
import com.mrsisa.tim22.model.SystemEntity;
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
public class SystemEntityDTO{

    private int id;
    private List<String> photos;
    private String name;
    private String description;
    private double price;
    private double averageScore;
    private int capacity;
    private String address;
    private String firstImage;
    private String type;
    private String rulesOfConduct;

    private String owner;
    private String ownersPhoneNumber;

    private String amenities;

    private double cancelationFee;


    public SystemEntityDTO(SystemEntity entity){
        this.id = entity.getId();
        this.photos =  entity.getPhotos();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.averageScore = entity.getAverageScore();
        this.capacity = entity.getCapacity();
        this.address = generateAddressString(entity.getAddress());
        if (entity.getPhotos().size() > 0){
            this.firstImage = entity.getPhotos().get(0);
        }
        else{
            firstImage = "";
        }
        this.type = String.valueOf(entity.getEntityType());
        this.rulesOfConduct = entity.getRulesOfConduct();
        this.owner = entity.getOwner().getName() + " " + entity.getOwner().getSurname();
        this.ownersPhoneNumber = entity.getOwner().getPhoneNumber();
        this.amenities = generateAmenitiesString(entity.getAmenities());
        this.cancelationFee = entity.getCancellationFee();
    }

    private String generateAmenitiesString(List<Amenity> amenities) {
        String amenityString = "";
        if (amenities.size() > 0){
            for (Amenity a:amenities){
                amenityString += String.valueOf(a);
                amenityString += ", ";
            }
            amenityString = amenityString.substring(0, amenityString.length()-2);
        }
        return amenityString;

    }

    private String generateAddressString(Address a) {

        String address = "";
        address += a.getStreetName();
        address += " ";
        address += String.valueOf(a.getStreetNumber());
        address += ", ";
        address += a.getCity();
        address += ", ";
        address += a.getCountry();
        return address;
    }

    public SystemEntityDTO(int id,String image, String name, double price, double rating, String address, String type, double cancelationFee){
        this.id = id;
        this.photos = new ArrayList<String>();
        this.photos.add(image);
        this.name = name;
        this.price = price;
        this.address = address;
        this.averageScore = rating;
        this.firstImage = image;
        this.type = type;
        this.cancelationFee = cancelationFee;
    }

}
