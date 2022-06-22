package com.mrsisa.tim22.repository;

import com.mrsisa.tim22.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(value="select r from Reservation r where r.client.id = ?1")
    List<Reservation> findByClient(int id);

    @Query(value="select r from Reservation r where r.systemEntity.owner.id= ?1")
    List<Reservation> findByOwner(int id);

    @Query(value="select r from Reservation r where r.systemEntity.id= ?1")
    List<Reservation> findByEntity(int id);

    Reservation findOneById(Integer entityId);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("SELECT r FROM Reservation r where r.id=:id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "1000")})
    Reservation getLockedReview(Integer id);






}
