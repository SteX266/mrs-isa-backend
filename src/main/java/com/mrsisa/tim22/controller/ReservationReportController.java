package com.mrsisa.tim22.controller;


import com.mrsisa.tim22.dto.ReservationReportDTO;
import com.mrsisa.tim22.service.ReservationReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = {"*"}, allowedHeaders = "*")
@RequestMapping(value = "/reservationReport", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationReportController {



    @Autowired
    private ReservationReportService reservationReportService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/getAllReservationReports")
    public ResponseEntity<ArrayList<ReservationReportDTO>> getAllReservationReports(){
        return new ResponseEntity<ArrayList<ReservationReportDTO>>(reservationReportService.getAllReservationReports(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/acceptReservationReports")
    public boolean acceptReservationReports(@RequestBody ReservationReportDTO dto){
        return reservationReportService.acceptReservationReports(dto);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/declineReservationReports")
    public boolean declineReservationReports(@RequestBody ReservationReportDTO dto){
        return reservationReportService.declineReservationReports(dto);
    }
}