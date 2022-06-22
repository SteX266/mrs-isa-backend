package com.mrsisa.tim22.controller;

import com.mrsisa.tim22.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"}, allowedHeaders = "*")
@RequestMapping(value = "/review", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReviewController {



    @Autowired
    private ReviewService reviewService;

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping(value = "/createReview")
    public ResponseEntity<String> createReview(@RequestParam int reservationId, @RequestParam String username, @RequestParam String text, @RequestParam int rating){
        boolean isSuccessful = reviewService.createReview(reservationId, username, text, rating);

        if (isSuccessful){
            return new ResponseEntity<>("OK", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Already reviewed!", HttpStatus.FORBIDDEN);
        }
    }

}
