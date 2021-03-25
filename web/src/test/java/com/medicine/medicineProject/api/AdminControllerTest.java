package com.medicine.medicineProject.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.medicine.medicineProject.dtos.AdminUpdateDto;
import com.medicine.medicineProject.models.Admin;
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
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp(){

    }

    @Test
    void registerAdminApiTest() throws Exception {
        Admin admin = new Admin();
        admin.setEmail("pay@gmail.com");
        admin.setPassword("password");

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post("/admin/register")
                .contentType("application/json")
                .content(mapper.writeValueAsString(admin)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    @DisplayName("Test that error is thrown when user exists already")
    void testDuplicateEntry() throws Exception {
        Admin newAdmin = new Admin();
        newAdmin.setEmail("teo@gmail.com");
        newAdmin.setPassword("password");

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(post("/admin/register")
        .contentType("application/json")
        .content(mapper.writeValueAsString(newAdmin)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void updateAdminProfileTest() throws  Exception{

        AdminUpdateDto adminUpdateDto = new AdminUpdateDto();
        adminUpdateDto.setFirstName("gafar");
        adminUpdateDto.setLastName("leona");

        ObjectMapper mapper = new ObjectMapper();

        this.mockMvc.perform(patch("/admin/update/900b77288j23322u122a8calf")
                .contentType("applcation/json")
                .content(mapper.writeValueAsString(adminUpdateDto)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
