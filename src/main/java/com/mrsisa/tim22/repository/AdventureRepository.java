package com.mrsisa.tim22.repository;

import com.mrsisa.tim22.model.Adventure;
import com.mrsisa.tim22.model.Vacation;
import com.mrsisa.tim22.model.Vessel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

public interface AdventureRepository extends JpaRepository<Adventure, Long> {
    public Adventure findAdventureById(int id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT a FROM Adventure a where a.id=:id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "1000")})
    Adventure getLockedEntity(Integer id);

}
