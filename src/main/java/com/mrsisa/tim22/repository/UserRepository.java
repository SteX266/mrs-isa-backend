package com.mrsisa.tim22.repository;

import com.mrsisa.tim22.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findOneById(Integer id);


    public User findOneByUsername(String username);

    public Page<User> findAll(Pageable pageable);


}
