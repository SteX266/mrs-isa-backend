package com.mrsisa.tim22.dto;

import com.mrsisa.tim22.model.Amenity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AmenitiesDTO {
    private List<Amenity> amenityList;
    private Integer serviceID;
}
