package com.mrsisa.tim22.controller;

import com.mrsisa.tim22.dto.CancellationRequestDTO;
import com.mrsisa.tim22.service.AccountCancellationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = {"*"}, allowedHeaders = "*")
@RequestMapping(value="/cancellationRequest", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountCancellationRequestController {

    @Autowired
    private AccountCancellationRequestService accountCancellationRequestService;

    @GetMapping("/createCancellationRequest")
    public void createCancellationRequest(@RequestParam String username, @RequestParam String text){

        accountCancellationRequestService.createNewCancellationRequest(username, text);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/getCancellationRequest")
    public ResponseEntity<ArrayList<CancellationRequestDTO>> getCancellationRequest(){

       return  new ResponseEntity<ArrayList<CancellationRequestDTO>> (accountCancellationRequestService.getCancellationRequest(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/acceptCancellationRequest")
    public boolean makeReservation(@RequestBody CancellationRequestDTO dto){
        return accountCancellationRequestService.acceptCancellationReques(dto);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/declineCancellationRequest")
    public boolean declineCancellationRequest(@RequestBody CancellationRequestDTO dto){
        return accountCancellationRequestService.declineCancellationRequest(dto);
    }


}
