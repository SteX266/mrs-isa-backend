package com.mrsisa.tim22.repository;

import com.mrsisa.tim22.model.SystemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.ArrayList;
import java.util.List;

public interface SystemEntityRepository extends JpaRepository<SystemEntity, Integer> {

    public SystemEntity findOneById(Integer id);

    public ArrayList<SystemEntity> findSystemEntitiesByOwner_Username(String email);


    public Page<SystemEntity> findAll(Pageable pageable);

    @Query(value="select e from SystemEntity e where e.id >= ?1 and e.id <= ?2")
    public List<SystemEntity> entitiesBetweenIds(int startId, int endId);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT e FROM SystemEntity e where e.id=:id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "1000")})
    SystemEntity getLockedEntity(Integer id);

}
