package com.medicine.medicineProject.repositories;

import com.medicine.medicineProject.models.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = DataConfig.class)
@ActiveProfiles("test")
@Slf4j
class PatientRepositoryTest {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Customer is saved to database")
    void customerCanBeSavedTest(){
        Calendar cal = Calendar.getInstance();
        cal.set(1995, 12, 1);

        Patient patient = new Patient();
        patient.setId("1");
        patient.setFirstName("Lois");
        patient.setDateOfBirth(cal.getTime());
        patient.setEmail("li@gmail.com");
        patient.setPassword("kkkkk");

        log.info("Customer instance --> {}", patient);

        Patient patient1 = patientRepository.save(patient);

        assertThat(patient1).isNotNull();
    }

    @Test
    @DisplayName("Find all customers in database")
    void findAllCustomersTest(){
        Patient newPatient1 = new Patient();
        newPatient1.setId("2");
        newPatient1.setFirstName("Gina");
        newPatient1.setLastName("Mendez");
        newPatient1.setDateOfBirth(new Date());
        newPatient1.setEmail("gm@gmail.com");
        newPatient1.setPassword("gggg");

        Patient newPatient2 = new Patient();
        newPatient2.setId("3");
        newPatient2.setFirstName("fiona");
        newPatient2.setLastName("charlie");
        newPatient2.setGender(Gender.FEMALE);
        newPatient2.setDateOfBirth(new Date());
        newPatient2.setEmail("fc@gmail.com");
        newPatient2.setAddress(new Address());
        newPatient2.setPassword("ffff");

        patientRepository.save(newPatient1);
        patientRepository.save(newPatient2);

        log.info("saved customer 1 {}, saved customer 2 {}", newPatient1.getId(), newPatient2.getId());

        List<Patient> customerList = patientRepository.findAll();

        assertThat(customerList.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("We can find customer by id")
    void findCustomerByIdTest(){
        Optional<Patient> savedCustomer = patientRepository.findById("2");

        assertThat(savedCustomer.get().getId()).isEqualTo("2");
    }

    @Test
    @DisplayName("Customer details in database can be updated")
    void updateCustomerDetailsTest(){
        Optional<Patient> savedCustomer = patientRepository.findById("2");
        log.info("saved customer last name before update is --> {}", savedCustomer.get().getLastName());
        assertThat(savedCustomer.get().getLastName()).isEqualTo("Mendez");

        savedCustomer.get().setLastName("Gomez");
        patientRepository.save(savedCustomer.get());
        log.info("saved customer last name after update is -->{}", savedCustomer.get().getLastName());
        assertThat(savedCustomer.get().getLastName()).isEqualTo("Gomez");
    }

    @Test
    @DisplayName("Customer can be deleted by id")
    void deleteCustomerByIdTest(){
        assertThat(patientRepository.existsById("3")).isTrue();

        patientRepository.deleteById("3");

        assertThat(patientRepository.existsById("3")).isFalse();
    }

    @Test
    @DisplayName("Customer can be found by email address")
    void findCustomerByEmailTest(){
        Patient patient = new Patient();
        patient.setId("5");
        patient.setEmail("ymail@gmail.com");
        patientRepository.save(patient);

       Patient foundPatient = patientRepository.findByEmail(patient.getEmail()).get();
        assertThat(foundPatient).isNotNull();
        assertThat(foundPatient.getEmail()).isEqualTo("email@gmail.com");
    }

//    @Test
//    void check_something(){
//        Patient patient = new Patient();
//        patient.setFirstName("Raphael");
//        patient.setLastName("Raph");
//        patient.setEmail("blabla@hotmail.com");
//        patient.setPassword("password");
////        patient.setAddress(new Address());
//        patientRepository.save(patient);
//        assertThat(patient.getId()).isNotNull();
//
//        Patient getPatient = patientRepository.findByEmail(patient.getEmail()).get();
//        assertThat(getPatient.getFirstName()).isEqualTo("Raphael");
//    }

    @Test
    void test_find_from_userRepo(){
        Patient patient = new Patient();
        patient.setFirstName("Raph");
        patient.setPassword("pass");
        patient.setEmail("raph@gmail.com");
        patientRepository.save(patient);

        User user = new User();
        userRepository.save(patient);
        User getUser = userRepository.findByEmail("raph@gmail.com").get();
        assertThat(getUser.getFirstName()).isEqualTo("Raph");

    }
}