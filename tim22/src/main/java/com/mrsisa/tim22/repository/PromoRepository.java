package com.mrsisa.tim22.repository;

import com.mrsisa.tim22.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PromoRepository extends JpaRepository<Promo, Integer> {

    @Query(value="select p from Promo p where p.systemEntity.id = ?1")
    List<Promo> findByEntity(int entityId);

    public Promo findOneById(int promoId);
    @Override
    <S extends Promo> S save(S s);

    @Override
    void deleteById(Integer integer);
}
