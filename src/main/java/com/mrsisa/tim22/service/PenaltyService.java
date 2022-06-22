package com.mrsisa.tim22.service;

import com.mrsisa.tim22.repository.PenaltyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PenaltyService {
    @Autowired
    private PenaltyRepository penaltyRepository;

}
