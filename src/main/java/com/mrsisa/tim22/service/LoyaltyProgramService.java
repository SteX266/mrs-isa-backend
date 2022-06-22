package com.mrsisa.tim22.service;

import com.mrsisa.tim22.model.LoyaltyProgram;
import com.mrsisa.tim22.repository.LoyaltyProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoyaltyProgramService {
    @Autowired
    LoyaltyProgramRepository loyaltyProgramRepository;

    public LoyaltyProgram getLoyalty() {
        return loyaltyProgramRepository.findAll().get(0);
    }

    public LoyaltyProgram save(LoyaltyProgram lp){
        return loyaltyProgramRepository.save(lp);
    }
}
