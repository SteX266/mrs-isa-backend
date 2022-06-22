package com.mrsisa.tim22.student3;

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

import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Student3IntegrationTest {
    private static final String URL_PREFIX = "/entity/";
    private static final String URL_PREFIX2 = "/loyalty/";

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
    public void testGetDetailAdventure() throws Exception {
        String url= URL_PREFIX+"/getDetailAdventure";
        this.mockMvc.perform(get(url).contentType(contentType)
                        .param("id","1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.price")
                        .value(150));;
    }
    @WithMockUser(roles="INSTRUCTOR")
    @Test
    public void testGetLoyalty() throws Exception {
        String url= URL_PREFIX2+"/getLoyalty";
        this.mockMvc.perform(get(url).contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.goldLimit")
                        .value(60));;
    }

    @WithMockUser(roles="ADMIN")
    @Test
    public void testgetEntityById() throws Exception {
        String url= URL_PREFIX+"/getEntityById";
        this.mockMvc.perform(get(url).contentType(contentType).param("id","12"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.capacity")
                        .value(8)).andExpect(jsonPath("$.averageScore")
                        .value(4));;
    }

}
