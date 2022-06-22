package com.mrsisa.tim22.repository;

import com.mrsisa.tim22.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository  extends JpaRepository<Complaint, Integer> {
}
