package com.mrsisa.tim22.student1;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrsisa.tim22.dto.ComplaintDTO;
import com.mrsisa.tim22.dto.FilterDTO;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.HashSet;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Student1IntegrationTest {

    private static final String URL_PREFIX = "/complaint";
    private static final String URL_PREFIX2 = "/review";
    private static final String URL_PREFIX3 = "/auth";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();

    }

    @WithMockUser(roles="CLIENT")
    @Test
    public void testCreateComplaint() throws Exception{

        ComplaintDTO dto = new ComplaintDTO();
        dto.setText("Very bad service");
        dto.setReservationId(1);

        String json=new ObjectMapper().writeValueAsString(dto);
        String url = URL_PREFIX+"/createComplaint";
        this.mockMvc.perform(post(url).contentType(contentType).content(json)).andExpect(status().isOk());

    }

    @WithMockUser(roles="CLIENT")
    @Test
    public void testCreateReview() throws Exception{

        String reservationId = "1";
        String username = "stefan.milosevic.e14@gmail.com";
        String text = "Dobra vikendica";


        String url = URL_PREFIX2+"/createReview";

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("reservationId", reservationId);
        params.add("username", username);
        params.add("text",text);
        params.add("rating","5");

        this.mockMvc.perform(get(url).contentType(contentType).params(params)).andExpect(status().isOk());

    }

    @WithMockUser(roles="CLIENT")
    @Test
    public void testGetFilteredEntitiesTotalNumber()throws Exception{
        FilterDTO filterDTO = new FilterDTO();
        filterDTO.setCancellationFeeFrom(0);
        filterDTO.setCancellationFeeTo(500);
        filterDTO.setCity("");
        filterDTO.setCountry("");
        filterDTO.setDateFrom(null);
        filterDTO.setDateTo(null);
        filterDTO.setEndIndex(6);
        filterDTO.setStartIndex(1);
        filterDTO.setGuestsFrom(1);
        filterDTO.setGuestsTo(10);
        filterDTO.setRentalFeeFrom(0);
        filterDTO.setRentalFeeTo(500);
        filterDTO.setStreet("");
        filterDTO.setType("SHOW_ALL");

        String json=new ObjectMapper().writeValueAsString(filterDTO);
        String url = URL_PREFIX3 +"/getFilteredEntitiesTotalNumber";
        this.mockMvc.perform(post(url).contentType(contentType).content(json)).andExpect(status().isOk());

    }

}
