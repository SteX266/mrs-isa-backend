package com.mrsisa.tim22.dto;

import com.mrsisa.tim22.model.RegistrationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistrationRequestDTO {
    private int id;
    private String client;
    private String description;
    private String role;

    public RegistrationRequestDTO(RegistrationRequest r){
        id = r.getId();
        client=r.getClient().getUsername();
        description = r.getDescription();
        role= r.getClient().getRoles().get(0).getName();
    }
}
