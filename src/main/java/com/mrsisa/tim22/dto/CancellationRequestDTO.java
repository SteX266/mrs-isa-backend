package com.mrsisa.tim22.dto;

import com.mrsisa.tim22.model.AccountCancellationRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CancellationRequestDTO {

    private Integer id;
    private String client;
    private String text;



    private String role;

    public CancellationRequestDTO(AccountCancellationRequest acr){
        this.text = acr.getText();
        this.id = acr.getId();
        this.client = acr.getUser().getUsername();
        this.role = acr.getUser().getRoles().get(0).getName();
    }
}
