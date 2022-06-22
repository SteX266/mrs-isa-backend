package com.mrsisa.tim22.repository;

import com.mrsisa.tim22.model.AccountCancellationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface AccountCancellationRequestRepository extends JpaRepository<AccountCancellationRequest, Integer> {
    public List<AccountCancellationRequest> findAccountCancellationRequestByUserUsername(String username);
}
