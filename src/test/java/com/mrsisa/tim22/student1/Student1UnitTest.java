package com.mrsisa.tim22.student1;

import com.mrsisa.tim22.model.*;
import com.mrsisa.tim22.repository.ReservationRepository;
import com.mrsisa.tim22.repository.SystemEntityRepository;
import com.mrsisa.tim22.service.ReservationService;
import com.mrsisa.tim22.service.SystemEntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class Student1UnitTest {
    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private SystemEntityRepository systemEntityRepository;
    @InjectMocks
    private ReservationService reservationService;
    @InjectMocks
    private SystemEntityService systemEntityService;

    @Test
    public void cancelingFalseReservation(){
        Reservation r = new Reservation();
        r.setDateFrom(LocalDateTime.now().plusDays(2));
        User u = new User();
        r.setClient(u);

        when(reservationRepository.findOneById(1)).thenReturn(r);
        boolean canCancel = reservationService.cancelReservation(1);

        assertFalse(canCancel);
    }

    @Test
    public void entityAvailability(){

        SystemEntity entity = new Vacation();

        Reservation r = new Reservation();
        AvailabilityPeriod period = new AvailabilityPeriod();
        period.setDateFrom(LocalDateTime.of(2022,6,22,12,0));
        period.setDateTo(LocalDateTime.of(2023,6,22,12,0));

        r.setDateFrom(LocalDateTime.of(2022,6,23,13,0));

        r.setDateTo(LocalDateTime.of(2022,6,28,13,0));

        Set<AvailabilityPeriod> periods = new HashSet<>();
        periods.add(period);

        Set<Reservation> reservations = new HashSet<>();
        reservations.add(r);

        entity.setReservations(reservations);
        entity.setAvailabilityPeriod(periods);

        assertTrue(reservationService.isEntityAvailable(entity, LocalDateTime.of(2022,6,29,17,15),LocalDateTime.of(2022,6,30,15,0)));


    }

    @Test
    public void idGeneration(){
        SystemEntity entity = new Adventure();
        entity.setId(1);
        SystemEntity entity2 = new Vacation();
        entity2.setId(2);
        SystemEntity entity3 = new Vessel();
        entity3.setId(3);
        SystemEntity entity4 = new Adventure();
        entity4.setId(4);
        List<SystemEntity> entities = new ArrayList<>();
        entities.add(entity);
        entities.add(entity2);
        entities.add(entity3);
        entities.add(entity4);

        when(systemEntityRepository.findAll()).thenReturn(entities);

        int id = systemEntityService.generateNextId();

        assertEquals(id,5);
    }


}
