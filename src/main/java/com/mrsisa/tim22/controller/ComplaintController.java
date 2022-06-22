package com.mrsisa.tim22.controller;

import com.mrsisa.tim22.dto.ComplaintDTO;
import com.mrsisa.tim22.dto.ReservationReportDTO;
import com.mrsisa.tim22.dto.ReviewDTO;
import com.mrsisa.tim22.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@CrossOrigin(origins = {"*"}, allowedHeaders = "*")
@RequestMapping(value = "/complaint", produces = MediaType.APPLICATION_JSON_VALUE)
public class ComplaintController {
    @Autowired
    ComplaintService complaintService;


    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping(value="/createComplaint")
    public ResponseEntity<String> createComplaint(@RequestBody ComplaintDTO complaintRequest){
        if(complaintService.createComplaint( complaintRequest.getReservationId(), complaintRequest.getText())){
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Can't write complaint", HttpStatus.FORBIDDEN);
        }
    }
    @PreAuthorize("hasAnyRole('ROLE_VACATION_OWNER','ROLE_SHIP_OWNER','ROLE_INSTRUCTOR')")
    @PostMapping(value="/createBussinessComplaint")
    public ResponseEntity<String> createBussinessComplaint(@RequestBody ComplaintDTO complaintRequest){
        if(complaintService.createBussinessComplaint( complaintRequest.getReservationId(), complaintRequest.getText(), complaintRequest.isUserShowedUp())){
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Can't write complaint", HttpStatus.FORBIDDEN);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/getAllComplaint")
    public ResponseEntity<ArrayList<ReservationReportDTO>> getAllComplaint(){
        return new ResponseEntity<ArrayList<ReservationReportDTO>>(complaintService.getAllComplaint(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/answerComplaint")
    public boolean answerComplaint(@RequestBody ReservationReportDTO dto){
        return complaintService.answerComplaint(dto);
    }
}
