package com.mrsisa.tim22.repository;

import com.mrsisa.tim22.model.LoyaltyProgram;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram, Integer> {
}