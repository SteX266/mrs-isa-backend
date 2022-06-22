package com.mrsisa.tim22.student2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrsisa.tim22.dto.AdventureDTO;
import com.mrsisa.tim22.dto.AvailabilityPeriodDTO;
import com.mrsisa.tim22.dto.GeneralDTO;
import com.mrsisa.tim22.dto.VesselDetailsDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Student2IntegrationTest {
    private static final String URL_PREFIX = "/entity/";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();

    }
    @WithMockUser(roles="INSTRUCTOR")
    @Test
    public void testCreateAdventure() throws Exception {
        AdventureDTO adventureDTO = new AdventureDTO();
        adventureDTO.setName("IME");
        adventureDTO.setDescription("OPIS");
        adventureDTO.setRulesOfConduct("PRAVILA");
        List<AvailabilityPeriodDTO> availabilityPeriodDTOS = new ArrayList<>();

        String dateFrom = "2022-06-22T17:24:01.252Z";
        String dateTo = "2022-06-22T17:24:01.252Z";
        AvailabilityPeriodDTO dto = new AvailabilityPeriodDTO();
        dto.setDateFrom(dateFrom);
        dto.setDateTo(dateTo);
        availabilityPeriodDTOS.add(dto);
        adventureDTO.setAvailabilityPeriod(availabilityPeriodDTOS);
        String json = new ObjectMapper().writeValueAsString(adventureDTO);
        String url= URL_PREFIX+"/createAdventure";
        this.mockMvc.perform(post(url).contentType(contentType).content(json)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "SHIP_OWNER")
    public void testEditVesselDetailsNoServiceID() throws Exception {
        VesselDetailsDTO vesselDetailsDTO = new VesselDetailsDTO();
        vesselDetailsDTO.setEngineNumber(10);
        vesselDetailsDTO.setEnginePower(10);
        vesselDetailsDTO.setMaxSpeed(10);
        String json = new ObjectMapper().writeValueAsString(vesselDetailsDTO);
        String url = URL_PREFIX + "/editVesselDetails";
        this.mockMvc.perform(post(url).contentType(contentType).content(json)).andExpect(status().isInternalServerError());
    }

    @Test
    @WithMockUser(roles = {"SHIP_OWNER", "INSTRUCTOR", "VACATION_OWNER"})
    public void testEditGeneral() throws Exception {
        GeneralDTO generalDTO = new GeneralDTO();
        generalDTO.setPrice(100);
        generalDTO.setCancellationFee(100);
        generalDTO.setRulesOfConduct("RULES");
        generalDTO.setCapacity(10);
        generalDTO.setName("IME");
        generalDTO.setServiceID(2);
        String json = new ObjectMapper().writeValueAsString(generalDTO);
        String url = URL_PREFIX + "/editGeneral";
        this.mockMvc.perform(post(url).contentType(contentType).content(json)).andExpect(status().isOk());

    }
}
