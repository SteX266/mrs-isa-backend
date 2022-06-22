package com.mrsisa.tim22.student2;

import com.mrsisa.tim22.dto.GeneralDTO;
import com.mrsisa.tim22.dto.VesselDTO;
import com.mrsisa.tim22.dto.VesselDetailsDTO;
import com.mrsisa.tim22.model.Reservation;
import com.mrsisa.tim22.model.SystemEntity;
import com.mrsisa.tim22.model.Vessel;
import com.mrsisa.tim22.repository.AddressRepository;
import com.mrsisa.tim22.repository.SystemEntityRepository;
import com.mrsisa.tim22.repository.VesselRepository;
import com.mrsisa.tim22.service.SystemEntityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Student2UnitTest {
    @Mock
    private VesselRepository vesselRepository;
    @Mock
    private AddressRepository addressRepository;

    @Mock
    private SystemEntityRepository systemEntityRepository;

    @Mock
    VesselDTO vesselDTOMock1;


    @Mock
    VesselDetailsDTO vesselDetailsDTO;

    @Mock
    Vessel vesselMock;


    @InjectMocks
    private SystemEntityService systemEntityService;

    @Test
    public void testEditGeneral() {
        Integer id = 100;
        GeneralDTO generalDTO = new GeneralDTO();
        generalDTO.setName("Novi sad");
        generalDTO.setDescription("Serbia");
        generalDTO.setServiceID(id);
        generalDTO.setCapacity(20);
        generalDTO.setCancellationFee(100);
        generalDTO.setPrice(100);
        generalDTO.setRulesOfConduct("Pravila");
        SystemEntity entity = new Vessel();
        entity.setId(id);
        when(systemEntityRepository.findOneById(id)).thenReturn(entity);
        when(systemEntityRepository.save(entity)).thenReturn(entity);

        SystemEntity saved = systemEntityService.editGeneral(generalDTO);

        boolean isValid = saved.getName().equals(generalDTO.getName())
                && saved.getDescription().equals(generalDTO.getDescription())
                && saved.getRulesOfConduct().equals(generalDTO.getRulesOfConduct())
                && saved.getCapacity() == generalDTO.getCapacity()
                && saved.getPrice() == generalDTO.getPrice()
                && saved.getCancellationFee() == generalDTO.getCancellationFee()
                && saved.getId() == generalDTO.getServiceID();
        assertTrue(isValid);
    }

    @Test
    public void testHasReservationCantDelete() {
        Integer id = 100;
        Reservation reservation = new Reservation();
        reservation.setDateFrom(LocalDateTime.now().plusWeeks(10));
        reservation.setDateTo(LocalDateTime.now().plusWeeks(11));
        SystemEntity vessel = new Vessel();

        vessel.setReservations(new HashSet<>());
        vessel.getReservations().add(reservation);
        when(systemEntityRepository.findOneById(id)).thenReturn(vessel);
        boolean successful = systemEntityService.deleteEntity(id);

        assertFalse(successful);

    }

    @Test
    public void testEditVesselDetails() {
        VesselDetailsDTO vesselDetailsDTO = new VesselDetailsDTO();
        vesselDetailsDTO.setEngineNumber(10);
        vesselDetailsDTO.setEnginePower(13);
        vesselDetailsDTO.setMaxSpeed(50);
        vesselDetailsDTO.setServiceID(10);
        Vessel vessel = new Vessel();
        vessel.setId(10);
        when(vesselRepository.findVesselById(10)).thenReturn(vessel);
        when(vesselRepository.save(vessel)).thenReturn(vessel);

        Vessel saved = systemEntityService.editVesselDetails(vesselDetailsDTO);

        boolean isSame = saved.getEngineNumber() == 10 && saved.getEnginePower() == 13 && saved.getMaxSpeed() == 50;
        assertTrue(isSame);

    }
}
