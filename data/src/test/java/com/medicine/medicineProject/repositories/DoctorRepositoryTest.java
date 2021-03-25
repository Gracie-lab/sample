package com.medicine.medicineProject.repositories;

import com.medicine.medicineProject.models.DataConfig;
import com.medicine.medicineProject.models.Doctor;
import com.medicine.medicineProject.models.Address;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = DataConfig.class)
@ActiveProfiles("test")
@Slf4j
class DoctorRepositoryTest {

    @Autowired
    DoctorRepository doctorRepository;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testThatDoctorCanBeSaved(){
        Doctor doctor = new Doctor();
        doctor.setId("1");
        doctor.setFirstName("Femi");
        doctor.setPassword("femo");
        doctor.setEmail("fem@gmail.com");

        Doctor newDoctor = doctorRepository.save(doctor);

        assertThat(newDoctor).isNotNull();
    }

    @Test
    @DisplayName("This test case tests that an address can be mapped to a doctor")
    void doctorHasAddress(){
        Address address = new Address();
        address.setStreetNumber("14");
        address.setStreetName("Karimu Ikotun");
        address.setId("7");
        Doctor doctor = new Doctor();
        doctor.setId("2");
        doctor.setAddress(address);
        Doctor newDoctor = doctorRepository.save(doctor);

        assertThat(newDoctor.getAddress()).isNotNull();
        assertThat(newDoctor.getAddress().getStreetName()).isEqualTo("Karimu Ikotun");
    }

    @Test
    void doctorDetailsCanBeUpdated(){
        Doctor doctor = new Doctor();
        doctor.setId("5");
        doctor.setFirstName("Sola");
        doctor.setLastName("Chukwu");
        doctor.setPassword("solachukwu");
        Doctor savedDoctor = doctorRepository.save(doctor);

        savedDoctor.setPassword("solachukwu1");
        doctorRepository.save(doctor);

        assertThat(savedDoctor.getPassword()).isEqualTo("solachukwu1");
    }

    @Test
    void doctorCanBeRemovedFromDatabase(){
        doctorRepository.deleteById("1");
        assertThat(doctorRepository.existsById("1")).isFalse();
    }

}