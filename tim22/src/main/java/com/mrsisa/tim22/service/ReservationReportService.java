package com.mrsisa.tim22.service;

import com.mrsisa.tim22.dto.ReservationReportDTO;
import com.mrsisa.tim22.model.*;
import com.mrsisa.tim22.repository.ReservationReportRepository;
import com.mrsisa.tim22.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReservationReportService {
    @Autowired
    private ReservationReportRepository reservationReportRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;


    public ArrayList<ReservationReportDTO> getAllReservationReports() {
        ArrayList<ReservationReportDTO> reportDTOs = new ArrayList<>();
        ArrayList<ReservationReport> reports = (ArrayList<ReservationReport>) reservationReportRepository.findAll();
        for (ReservationReport r: reports) {
            if(!r.isAnswered() && !r.isAutomaticPenalty()) {
                reportDTOs.add(new ReservationReportDTO(r));
            }
        }
        return reportDTOs;
    }



    public boolean acceptReservationReports(ReservationReportDTO dto) {
        User u =  userRepository.findOneByUsername(dto.getClient());

        if(u == null){
            return false;
        }
        ReservationReport rr = reservationReportRepository.findReservationReportById(dto.getId());
        rr.setAnswered(true);
            reservationReportRepository.save(rr);
        u.addPenalty(new Penalty(u));
        userRepository.save(u);
        emailService.sendReservationReportAccepted(dto.getClient(),dto.getOwner(),rr.getText(),dto.getText());
        return true;

    }


    public boolean declineReservationReports(ReservationReportDTO dto) {
        User u =  userRepository.findOneByUsername(dto.getClient());

        if(u == null){
            return false;
        }
        ReservationReport rr = reservationReportRepository.findReservationReportById(dto.getId());
        rr.setAnswered(true);
        reservationReportRepository.save(rr);
        emailService.sendReservationReportDeclined(dto.getClient(),dto.getOwner(),rr.getText(),dto.getText());
        return true;
    }

}
