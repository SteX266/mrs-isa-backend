package com.mrsisa.tim22.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    String email;
    String name;
    String surname;
    String phoneNumber;
    String addressLine;
    int loyaltyPoints;
    int penalties;
    String tier;
    int benefits;
}
