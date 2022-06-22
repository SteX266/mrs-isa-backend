package com.mrsisa.tim22.service;

import com.mrsisa.tim22.dto.CancellationRequestDTO;
import com.mrsisa.tim22.model.AccountCancellationRequest;
import com.mrsisa.tim22.model.User;
import com.mrsisa.tim22.repository.AccountCancellationRequestRepository;
import com.mrsisa.tim22.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountCancellationRequestService {

    @Autowired
    private AccountCancellationRequestRepository accountCancellationRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public void createNewCancellationRequest(String username, String text) {
        User u = userRepository.findOneByUsername(username);

        AccountCancellationRequest acr = new AccountCancellationRequest(text,false, u);

        accountCancellationRequestRepository.save(acr);


    }

    public  ArrayList<CancellationRequestDTO> getCancellationRequest() {
        ArrayList<CancellationRequestDTO> requests = new ArrayList<>();
        for (AccountCancellationRequest acr: accountCancellationRequestRepository.findAll()){
            if(!acr.isAnswered()) {
                requests.add(new CancellationRequestDTO(acr));
            }
        }
        return requests;
    }

    public boolean acceptCancellationReques(CancellationRequestDTO dto) {
            User u =  userRepository.findOneByUsername(dto.getClient());

            if(u == null){
                return false;
            }
            for (AccountCancellationRequest acr: accountCancellationRequestRepository.findAccountCancellationRequestByUserUsername(u.getUsername())) {
                acr.setAnswered(true);
                accountCancellationRequestRepository.save(acr);
            }
            u.setDeleted(true);
            u.setEnabled(false);
            userRepository.save(u);
            emailService.deleteRequestApprovedEmail(dto.getClient());
            return true;

    }

    public boolean declineCancellationRequest(CancellationRequestDTO dto) {
        User u =  userRepository.findOneByUsername(dto.getClient());

        if(u == null){
            return false;
        }
        for (AccountCancellationRequest acr: accountCancellationRequestRepository.findAccountCancellationRequestByUserUsername(u.getUsername())) {
            acr.setAnswered(true);
            accountCancellationRequestRepository.save(acr);
        }

        emailService.sendEmail(dto.getClient(),"Account Cancellation Request Declined",dto.getText());
        return true;
    }
}
