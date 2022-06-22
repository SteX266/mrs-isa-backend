package com.mrsisa.tim22.service;

import com.mrsisa.tim22.dto.PromoDTO;
import com.mrsisa.tim22.model.Promo;
import com.mrsisa.tim22.model.SystemEntity;
import com.mrsisa.tim22.repository.PromoRepository;
import com.mrsisa.tim22.repository.SystemEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PromoService {

    @Autowired
    private PromoRepository promoRepository;

    @Autowired
    private SystemEntityRepository systemEntityRepository;

    public ArrayList<PromoDTO> getEntityPromos(int id) {
        ArrayList<Promo> promos = (ArrayList<Promo>) promoRepository.findByEntity(id);
        ArrayList<PromoDTO> promoDTOS = new ArrayList<PromoDTO>();
        for(Promo p:promos){
            if (!p.isTaken()) {
                PromoDTO promoDTO = new PromoDTO(p);
                promoDTOS.add(promoDTO);
            }
        }

        return promoDTOS;
    }

    public boolean createPromoFromDTO(PromoDTO promoDTO ) {
        SystemEntity entity = systemEntityRepository.findOneById(promoDTO.getSystemEntityId());




        promoRepository.save(new Promo(promoDTO, entity));
        return true;
    }
    public boolean deleteById(Integer id) {
        Promo promo = promoRepository.getOne(id);
        if(promo.isTaken()) {
            return false;
        }
        promoRepository.deleteById(id);
        return true;
    }
}
