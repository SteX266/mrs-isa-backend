package com.mrsisa.tim22.repository;

import com.mrsisa.tim22.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    public Review findReviewById(int id);
}
