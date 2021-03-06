package com.mrsisa.tim22.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String addressLine;
    private String password;
    private String phoneNumber;
    private String userType;
    private String registrationReason;
}
