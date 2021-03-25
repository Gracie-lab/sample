package com.medicine.medicineProject.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.medicine.medicineProject.models.Hospital;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HospitalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){
    }

    @Test
    void registerHospitalApiTest() throws Exception {
        Hospital newHospital = new Hospital();
        newHospital.setEmail("joj@gmail.com");
        newHospital.setPassword("password");

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post("/hospital/register")
                .contentType("application/json")
                .content(mapper.writeValueAsString(newHospital)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @DisplayName("Test that error 404 is thrown when user exists already")
    void testForDuplicateInput() throws Exception{
        Hospital newHospital = new Hospital();
        newHospital.setEmail("jojo@gmail.com");
        newHospital.setPassword("password");

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post("/hospital/register")
                .contentType("application/json")
                .content(mapper.writeValueAsString(newHospital)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName("This test should throw a bad request error due to bad input -> invalid email")
    void testForBadInput() throws Exception {
        Hospital hospital = new Hospital();
        hospital.setEmail("josh.com");
        hospital.setPassword("@0987");

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post("/hospital/register")
                .contentType("application/json")
                .content(mapper.writeValueAsString(hospital)))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

}
