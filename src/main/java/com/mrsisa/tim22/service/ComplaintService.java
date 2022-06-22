package com.mrsisa.tim22.service;

import com.mrsisa.tim22.model.*;
import com.mrsisa.tim22.repository.ComplaintRepository;
import com.mrsisa.tim22.repository.ReservationReportRepository;
import com.mrsisa.tim22.repository.ReservationRepository;
import com.mrsisa.tim22.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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
}
