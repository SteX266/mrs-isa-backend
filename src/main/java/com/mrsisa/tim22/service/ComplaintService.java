package com.mrsisa.tim22.service;

import com.mrsisa.tim22.dto.ReservationReportDTO;
import com.mrsisa.tim22.model.*;
import com.mrsisa.tim22.repository.ComplaintRepository;
import com.mrsisa.tim22.repository.ReservationReportRepository;
import com.mrsisa.tim22.repository.ReservationRepository;
import com.mrsisa.tim22.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ComplaintService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ComplaintRepository complaintRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationReportRepository reservationReportRepository;
    @Autowired
    private EmailService emailService;
    public boolean createComplaint(int reservationId, String text) {
        Reservation reservation = reservationRepository.findOneById(reservationId);
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = userRepository.findOneByUsername(user.getUsername());
        SystemEntity e = reservation.getSystemEntity();
        Complaint complaint = new Complaint(text, u, reservation.getSystemEntity());
        e.addComplaint(complaint);
        complaintRepository.save(complaint);
        return true;
    }

    public boolean createBussinessComplaint(int reservationId, String text, boolean userShowedUp) {

        Reservation reservation = reservationRepository.findOneById(reservationId);
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = userRepository.findOneByUsername(user.getUsername());

        ReservationReport reservationReport = new ReservationReport(text,userShowedUp,u, reservation);
        reservationReportRepository.save(reservationReport);
        return true;
    }



    public ArrayList<ReservationReportDTO> getAllComplaint() {
        ArrayList<ReservationReportDTO> reportDTOs = new ArrayList<>();
        ArrayList<Complaint> reports = (ArrayList<Complaint>) complaintRepository.findAll();
        for (Complaint r: reports) {
            if(!r.isAnswered()) {
                reportDTOs.add(new ReservationReportDTO(r));
            }
        }
        return reportDTOs;
    }

    public boolean answerComplaint(ReservationReportDTO dto) {

        Complaint rr = complaintRepository.findComplaintById(dto.getId());
        rr.setAnswered(true);
        complaintRepository.save(rr);
        emailService.sendComplaintEmail(rr.getSender().getUsername(),rr.getSystemEntity().getOwner().getUsername(), rr.getText(),dto.getText());
        return true;

    }
}
