package com.mrsisa.tim22.repository;

import com.mrsisa.tim22.model.Address;
import com.mrsisa.tim22.model.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
