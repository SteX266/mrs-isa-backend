package com.mrsisa.tim22.service;

import com.mrsisa.tim22.dto.RegistrationRequestDTO;
import com.mrsisa.tim22.model.RegistrationRequest;
import com.mrsisa.tim22.model.User;
import com.mrsisa.tim22.repository.RegistrationRequestRepository;
import com.mrsisa.tim22.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RegistrationRequestService {
    @Autowired
    private RegistrationRequestRepository registrationRequestRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;


    public ArrayList<RegistrationRequestDTO> getAllRegistrationRequests() {
        ArrayList<RegistrationRequestDTO> requrstDTOs = new ArrayList<>();
        ArrayList<RegistrationRequest> requests = (ArrayList<RegistrationRequest>) registrationRequestRepository.findAll();
        for (RegistrationRequest r: requests) {
           if(!r.getIsAnswered()) {
               requrstDTOs.add(new RegistrationRequestDTO(r));
           }
        }
        return requrstDTOs;
    }

    public boolean acceptRegistrationRequest(RegistrationRequestDTO dto) {
       User u =  userRepository.findOneByUsername(dto.getClient());

        if(u == null){
            return false;
        }
        for (RegistrationRequest r: registrationRequestRepository.findRegistrationRequestsByClient(u)) {
            r.setIsAnswered(true);
            registrationRequestRepository.save(r);
        }
        u.setEnabled(true);
        userRepository.save(u);
        emailService.sendOwnerActivationEmail(dto.getClient());
        return true;
    }

    public boolean declineRegistrationRequest(RegistrationRequestDTO dto) {
        User u =  userRepository.findOneByUsername(dto.getClient());

        if(u == null){
            return false;
        }
        for (RegistrationRequest r: registrationRequestRepository.findRegistrationRequestsByClient(u)) {
            r.setIsAnswered(true);
            registrationRequestRepository.save(r);
        }

        emailService.sendEmail(dto.getClient(),"Reqistration Request Declined",dto.getDescription());
        return true;
    }
}
