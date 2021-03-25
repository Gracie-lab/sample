package com.medicine.medicineProject.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medicine.medicineProject.dtos.UserLoginDTO;
import com.medicine.medicineProject.models.Patient;
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
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void registerCustomerApiTest() throws Exception {
        Patient newPatient = new Patient();
        newPatient.setEmail("jjj@gmail.com");
        newPatient.setPassword("777");

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post("/customer/register")
                .contentType("application/json")
                .content(mapper.writeValueAsString(newPatient)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void customerCanRegisterWithValidPhoneNumber() throws Exception {
        Patient patient = new Patient();
        patient.setPhoneNumber("07066176835");
        patient.setPassword("password");

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post("/customer/register")
        .contentType("application/json")
        .content(mapper.writeValueAsString(patient)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @DisplayName("Test that error 404 is thrown when user exists already")
    void testDuplicateEntry() throws Exception {
        Patient newPatient = new Patient();
        newPatient.setEmail("tayo@gmail.com");
        newPatient.setPassword("777");

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post("/customer/register")
                .contentType("application/json")
                .content(mapper.writeValueAsString(newPatient)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName("This test should throw a bad request error due to bad input -> invalid email")
    void testForBadInput() throws Exception {
        Patient patient = new Patient();
        patient.setEmail("somail");
        patient.setPassword("pass");

        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(post("/customer/register")
                .contentType("application/json")
                .content(mapper.writeValueAsString(patient)))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("This test should throw a bad request error due to bad input -> invalid phone number")
    void testForBadPhoneNumberInput() throws Exception {
        Patient patient = new Patient();
        patient.setPhoneNumber("01rf56");
        patient.setPassword("pass");

        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(post("/customer/register")
                .contentType("application/json")
                .content(mapper.writeValueAsString(patient)))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    void can_patient_login() throws Exception {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail("email@gmail.com");
        userLoginDTO.setPassword("password");

        ObjectMapper mapper = new ObjectMapper();
        this.mockMvc.perform(post("/customer/login")
                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}