package com.mrsisa.tim22.repository;

import com.mrsisa.tim22.model.SystemEntity;
import com.mrsisa.tim22.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

public interface VacationRepository extends JpaRepository<Vacation, Long> {
    public Vacation findVacationById(int id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT v FROM Vacation v where v.id=:id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "1000")})
    Vacation getLockedEntity(Integer id);
}
