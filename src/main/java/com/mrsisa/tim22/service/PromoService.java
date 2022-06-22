package com.mrsisa.tim22.service;

import com.mrsisa.tim22.dto.PromoDTO;
import com.mrsisa.tim22.model.Promo;
import com.mrsisa.tim22.model.SystemEntity;
import com.mrsisa.tim22.model.User;
import com.mrsisa.tim22.repository.PromoRepository;
import com.mrsisa.tim22.repository.SystemEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
public class PromoService {

    @Autowired
    private PromoRepository promoRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ReservationService reservationService;

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

    @Transactional
    public boolean createPromoFromDTO(PromoDTO promoDTO ) {
        SystemEntity entity = systemEntityRepository.getLockedEntity(promoDTO.getSystemEntityId());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateFrom = LocalDateTime.parse(promoDTO.getDateFrom().replace("T"," ").substring(0,16), formatter);
        LocalDateTime dateTo = LocalDateTime.parse(promoDTO.getDateTo().replace("T"," ").substring(0,16), formatter);
        if(reservationService.isEntityAvailable(entity, dateFrom, dateTo)) {
            promoRepository.save(new Promo(promoDTO, entity));
            for (User sub: entity.getSubscribers()) {
                emailService.sendPromoEmail(sub.getUsername(), String.valueOf(entity.getEntityType()), entity.getName());
            }
            return true;
        }

        return false;
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
