package com.mrsisa.tim22.student3;

import com.mrsisa.tim22.dto.CancellationRequestDTO;
import com.mrsisa.tim22.dto.RevenurReportDTO;
import com.mrsisa.tim22.model.*;
import com.mrsisa.tim22.repository.AccountCancellationRequestRepository;
import com.mrsisa.tim22.repository.SystemEntityRepository;
import com.mrsisa.tim22.service.AccountCancellationRequestService;
import com.mrsisa.tim22.service.SystemEntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class UnitTests {
    @Mock
    private SystemEntityRepository systemEntityRepository;

    @InjectMocks
    private SystemEntityService systemEntityService;

    @Mock
    private AccountCancellationRequestRepository accountCancellationRequestRepository;

    @InjectMocks
    private AccountCancellationRequestService accountCancellationRequestService;

    @Test
    public void getWorstRated(){
        Adventure a1 = new Adventure();
        a1.setAverageScore(4);
        Adventure a2 = new Adventure();
        a2.setAverageScore(2);
        Adventure a3 = new Adventure();
        a3.setAverageScore(1);
        ArrayList<SystemEntity> adventures = new ArrayList<>();
        adventures.add(a1);
        adventures.add(a2);
        adventures.add(a3);

        when(systemEntityRepository.findSystemEntitiesByOwner_Username("pera@gmail.com"))
                .thenReturn(adventures);
        SystemEntity s  = systemEntityService.getWorstRatedUsersEntity("pera@gmail.com");
        double expected =s.getAverageScore();
        double actual = 1;
        assertEquals(expected,actual,10);
    }

    @Test
    public void RevenueReportAdmin(){
        Adventure a = new Adventure();
        a.setEntityType(SystemEntityType.ADVENTURE);
        Reservation r = new Reservation();
        r.setDateFrom(LocalDateTime.now());
        r.setDateFrom(LocalDateTime.now().plusWeeks(1));
        r.setOwnerPrice(200);
        r.setClientPrice(250);
        r.setSystemEntity(a);
        Set<Reservation> reservations = new HashSet<>();
        reservations.add(r);
        a.setReservations(reservations);
        ArrayList<SystemEntity> entities = new ArrayList<>();
        entities.add(a);

        when(systemEntityRepository.findAll())
                .thenReturn(entities);
        ArrayList<RevenurReportDTO> dtos = (ArrayList<RevenurReportDTO>) systemEntityService.getRevenueReportDataAdmin("2019-01-11 11:11","2029-01-11 11:11");
        boolean isCorrect = dtos.get(0).getRevenue() == 50 &&   dtos.get(1).getRevenue() == 0&&  dtos.get(2).getRevenue() == 0;
        assertTrue(isCorrect);
    }
    @Test
    public void getCancellationRequest(){
        User u = new User();
        u.setUsername("paja");
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role());
        u.setRoles(roles);
        ArrayList<AccountCancellationRequest> requests = new ArrayList<>();
        AccountCancellationRequest a1 = new AccountCancellationRequest();
        a1.setAnswered(false);
        a1.setUser(u);
        AccountCancellationRequest a2 = new AccountCancellationRequest();
        a2.setAnswered(false);
        a2.setUser(u);
        AccountCancellationRequest a3 = new AccountCancellationRequest();
        a3.setAnswered(false);
        a3.setUser(u);
        AccountCancellationRequest a4 = new AccountCancellationRequest();
        a4.setAnswered(false);
        a4.setUser(u);
        AccountCancellationRequest a5 = new AccountCancellationRequest();
        a5.setAnswered(true);
        a5.setUser(u);
        AccountCancellationRequest a6 = new AccountCancellationRequest();
        a6.setAnswered(true);
        a6.setUser(u);
        AccountCancellationRequest a7 = new AccountCancellationRequest();
        a7.setAnswered(true);
        a7.setUser(u);
        requests.add(a1);
        requests.add(a2);
        requests.add(a3);
        requests.add(a4);
        requests.add(a5);
        requests.add(a6);
        requests.add(a7);
        when(accountCancellationRequestRepository.findAll()).thenReturn(requests);
        ArrayList<CancellationRequestDTO> requestsDTO = accountCancellationRequestService.getCancellationRequest();
        assertThat(requestsDTO,hasSize(4));
    }

}

