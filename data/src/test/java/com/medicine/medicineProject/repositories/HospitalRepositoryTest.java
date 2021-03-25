//package com.medicine.medicineProject.repositories;
//
//import com.medicine.medicineProject.models.DataConfig;
//import com.medicine.medicineProject.models.Address;
//import com.medicine.medicineProject.models.Hospital;
//import com.medicine.medicineProject.models.Role;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(classes = DataConfig.class)
//@ActiveProfiles("test")
//@Slf4j
//class HospitalRepositoryTest {
//
//    @Autowired
//    HospitalRepository hospitalRepository;
//
//    @Autowired
//    AddressRepository addressRepository;
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @Test
//    @DisplayName("Hospital is saved to database")
//    void hospitalDetailsCanBeSavedTest(){
//        Address anAddress = new Address();
//        anAddress.setId("3");
//
//        addressRepository.save(anAddress);
//
//        Hospital hospital = new Hospital();
//        hospital.setId("1");
//        hospital.setFirstName("Dolapo");
//        hospital.setFirstName("Adeniyi");
//        hospital.setHospitalName("Divine Specialist Hospital");
//        hospital.setEmail("dsh@divine.ng");
//        hospital.setAddress(anAddress);
//        hospital.setPassword("password");
//        hospital.setRole(Role.HOSPITAL);
//
//        Hospital newHospital = hospitalRepository.save(hospital);
//        log.info("Hospital instance --> {}", hospital);
//        assertThat(hospital).isNotNull();
//    }
//
//    @Test
//    @DisplayName("Find all hospitals in database")
//    void findAllHospitalsTest(){
//        Hospital hospital = new Hospital();
//        hospital.setId("2");
//        hospital.setFirstName("Lilian");
//        hospital.setLastName("Okafor");
//        hospital.setHospitalName("Medic Specialist Hospital");
//        hospital.setEmail("msh@medic.ng");
//        hospital.setPassword("password2");
//        hospital.setRole(Role.HOSPITAL);
//
//        hospitalRepository.save(hospital);
//
//        List<Hospital> hospitalList = hospitalRepository.findAll();
//
//        log.info(" -> {}", hospitalList.size());
//        assertThat(hospitalList.size()).isEqualTo(2);
//    }
//
//    @Test
//    @DisplayName("We can find hospital by id")
//    void findHospitalByIdTest(){
//        Optional<Hospital> savedHospital = hospitalRepository.findById("1");
//
//        assertThat(savedHospital.get().getId()).isEqualTo("1");
//    }
//
//    @Test
//    @DisplayName("Hospital details in database can be updated")
//    void updateHospitalDetailsTest(){
//        Optional<Hospital> savedHospital = hospitalRepository.findById("1");
//        log.info("savedHospital name is --> {}", savedHospital.get().getHospitalName());
//        assertThat(savedHospital.get().getHospitalName()).isEqualTo("Divine Specialist Hospital");
//
//        savedHospital.get().setHospitalName("General Hospital");
//
//        hospitalRepository.save(savedHospital.get());
//        log.info("savedAddress state after update is -->{}", savedHospital.get().getHospitalName());
//        assertThat(savedHospital.get().getHospitalName()).isEqualTo("General Hospital");
//    }
//
//    @Test
//    @DisplayName("Hospital can be deleted by id")
//    void deleteHospitalByIdTest(){
//        assertThat(hospitalRepository.existsById("2")).isTrue();
//
//        hospitalRepository.deleteById("2");
//
//        assertThat(hospitalRepository.existsById("2")).isFalse();
//    }
//}