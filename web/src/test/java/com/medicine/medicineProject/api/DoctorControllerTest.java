package com.medicine.medicineProject.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.medicine.medicineProject.dtos.DoctorUpdateDto;
import com.medicine.medicineProject.models.Doctor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() { }

    @AfterEach
    void tearDown(){ }

    @Test
    void doctorControllerAPITest() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setEmail("id@gmail.com");
        doctor.setPassword("password");

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post("/doctor/register")
                .contentType("application/json")
                .content(mapper.writeValueAsString(doctor)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @DisplayName("Test that error 404 is thrown when user already exists")
    void testDuplicateEntry() throws Exception {
        Doctor newDoctor = new Doctor();
        newDoctor.setEmail("id@gmail.com");
        newDoctor.setPassword("password");

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post("/doctor/register")
                .contentType("application/json")
                .content(mapper.writeValueAsString(newDoctor)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

    }

    @Test
    @DisplayName("This test should throw a bad request error due to bad input -> invalid email")
    void testForBadInput() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setEmail("jo");
        doctor.setPassword("password");

        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(post("/doctor/register")
        .contentType("application/json")
        .content(mapper.writeValueAsString(doctor)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    void updateDoctorProfileApiTest() throws Exception {

        DoctorUpdateDto doctorUpdateDto = new DoctorUpdateDto();
        doctorUpdateDto.setFirstName("Trey");
        doctorUpdateDto.setLastName("Daniels");

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(patch("/doctor/update/60351e462199fc238013529e")
        .contentType("application/json")
        .content(mapper.writeValueAsString(doctorUpdateDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
