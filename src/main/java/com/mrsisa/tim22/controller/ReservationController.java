package com.mrsisa.tim22.controller;


import com.mrsisa.tim22.dto.ReservationDTO;
import com.mrsisa.tim22.dto.ReservationRequestDTO;
import com.mrsisa.tim22.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"}, allowedHeaders = "*")
@RequestMapping(value = "/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping(value = "/getClientReservations")
    public ResponseEntity<ArrayList<ReservationDTO>> getClientReservations(@RequestParam String email){
        return new ResponseEntity<ArrayList<ReservationDTO>>(reservationService.getClientReservations(email), HttpStatus.OK);
    }
    @GetMapping(value = "/getEntityReservations")
    public ResponseEntity<ArrayList<ReservationDTO>> getEntityReservations(@RequestParam int id){
        return new ResponseEntity<ArrayList<ReservationDTO>>(reservationService.getEntityReservations(id), HttpStatus.OK);
    }
    @GetMapping(value = "/getOwnerReservations")
    public ResponseEntity<ArrayList<ReservationDTO>> getOwnerReservations(@RequestParam String email){
        return new ResponseEntity<ArrayList<ReservationDTO>>(reservationService.getOwnerReservations(email), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping(value = "/cancelReservation")
    public ResponseEntity<String>cancelReservation(@RequestParam int entityId){



        if (reservationService.cancelReservation(entityId)){
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Too late to cancel.", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/approveReservation")
    public void approveReservation(@RequestParam int entityId){
        reservationService.approveReservation(entityId);
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping(value = "/createPromoReservation")
    public ResponseEntity<String> createPromoReservation(@RequestParam int promoId, @RequestParam String username){

        if(reservationService.createPromoReservation(promoId, username)){
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Can't make reservation", HttpStatus.FORBIDDEN);
        }
    }
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping(value = "/makeReservation")
    public ResponseEntity<String> makeReservation(@RequestBody ReservationRequestDTO reservationRequest){
        if(reservationService.makeReservation(reservationRequest)){
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Can't make reservation", HttpStatus.FORBIDDEN);
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @PostMapping("/makeReservationForClient")
    public ResponseEntity<String> makeReservationForClient(@RequestBody ReservationRequestDTO reservationRequestDTO) {
        if(reservationService.makeReservationForClient(reservationRequestDTO)) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Couldn't create reservation", HttpStatus.FORBIDDEN);
        }
    }

}
