package com.medicine.medicineProject.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medicine.medicineProject.models.Pharmacy;
import org.junit.jupiter.api.AfterEach;
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
class PharmacyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void pharmacyControllerAPITest() throws Exception {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setEmail("FMCA@gmail.com");
        pharmacy.setPassword("precious");
        //pharmacy.setId(1);

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post("/pharmacy/register")
                .contentType("application/json")
                .content(mapper.writeValueAsString(pharmacy)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @DisplayName("Test that error 404 is thrown when user exists already")
    void testDuplicateEntry() throws Exception {
        Pharmacy newPharmacy = new Pharmacy();
        newPharmacy.setEmail("FMC@gmail.com");
        newPharmacy.setPassword("777");

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post("/pharmacy/register")
                .contentType("application/json")
                .content(mapper.writeValueAsString(newPharmacy)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName("This test should throw a bad request error due to bad input -> invalid email")
    void testForBadInput() throws Exception {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setEmail("somemail");
        pharmacy.setPassword("pass");

        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(post("/pharmacy/register")
                .contentType("application/json")
                .content(mapper.writeValueAsString(pharmacy)))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }
}