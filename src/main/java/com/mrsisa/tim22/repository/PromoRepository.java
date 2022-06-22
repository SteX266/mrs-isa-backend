package com.mrsisa.tim22.repository;

import com.mrsisa.tim22.model.Promo;
import com.mrsisa.tim22.model.SystemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface PromoRepository extends JpaRepository<Promo, Integer> {

    @Query(value="select p from Promo p where p.systemEntity.id = ?1")
    List<Promo> findByEntity(int entityId);

    public Promo findOneById(int promoId);
    @Override
    <S extends Promo> S save(S s);

    @Override
    void deleteById(Integer integer);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("SELECT p FROM Promo p where p.systemEntity.id = ?1")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value = "1000")})
    List<Promo> getLockedPromoByEntityId(Integer id);

}
