package com.mrsisa.tim22.service;

import com.mrsisa.tim22.dto.ReservationReportDTO;
import com.mrsisa.tim22.dto.ReviewDTO;
import com.mrsisa.tim22.model.*;
import com.mrsisa.tim22.repository.ReservationRepository;
import com.mrsisa.tim22.repository.ReviewRepository;
import com.mrsisa.tim22.repository.SystemEntityRepository;
import com.mrsisa.tim22.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class ReviewService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private SystemEntityRepository systemEntityRepository;

    @Autowired
    private EmailService emailService;



    @Transactional
    public boolean createReview(int reservationId, String username, String text, int rating) {

        Reservation reservation = reservationRepository.getLockedReview(reservationId);
        SystemEntity e = reservation.getSystemEntity();
        User u = userRepository.findOneByUsername(username);


        System.out.println("Duzina: " + e.getComplaints().size() );
        for (Review r:e.getReviews()){
            if (r.getClient().getUsername().equals(username)){
                return false;
            }
        }
        Review review = new Review(rating, text, u, reservation.getSystemEntity());
        reservation.getSystemEntity().addReview(review);
        SystemEntity entity = reservation.getSystemEntity();

        double avg = 0;

        for(Review r:entity.getReviews()){
            avg+=r.getScore();
        }

        entity.setAverageScore(avg/entity.getReviews().size());


        reviewRepository.save(review);
        systemEntityRepository.save(entity);
        return true;

    }

    public ArrayList<ReviewDTO> getAllReviws() {
            ArrayList<ReviewDTO> reportDTOs = new ArrayList<>();
            ArrayList<Review> reports = (ArrayList<Review>) reviewRepository.findAll();
            for (Review r: reports) {
                if(!r.isApproved()) {
                    reportDTOs.add(new ReviewDTO(r));
                }
            }
            return reportDTOs;
        }

    public boolean acceptReviw(ReviewDTO dto) {
        Review rr = reviewRepository.findReviewById(dto.getId());
        rr.setApproved(true);
        reviewRepository.save(rr);
        emailService.sendReviewEmail(rr.getSystemEntity().getOwner().getUsername(),rr.getText(),rr.getClient().getUsername(),rr.getScore(),rr.getSystemEntity().getName());
        return true;
    }

    public boolean declineReviw(ReviewDTO dto) {
        Review rr = reviewRepository.findReviewById(dto.getId());
        reviewRepository.delete(rr);
        emailService.sendReservationReportDeclined(dto.getClient(),dto.getOwner(),rr.getText(),dto.getText());
        return true;
    }
}
