package com.medicine.medicineProject.security;

import com.medicine.medicineProject.config.SecurityConfig;
import com.medicine.medicineProject.models.Patient;
import com.medicine.medicineProject.repositories.PatientRepository;
import com.medicine.medicineProject.repositories.UserRepository;
import com.medicine.medicineProject.service.UserPrincipalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = SecurityConfig.class)
@Slf4j
class UserPrincipalServiceTest {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserPrincipalService userPrincipalService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void can_load_user_by_username(){
        Patient patient = new Patient();
        patient.setEmail("grace@gmail.com");
        patient.setPassword("password");
        patientRepository.save(patient);
        userRepository.save(patient);

      UserDetails userDetails = userPrincipalService.loadUserByUsername("grace@gmail.com");
        log.info("User details -> {}", userDetails);
        assertThat(userDetails.getUsername()).isEqualTo("grace@gmail.com");

    }
}