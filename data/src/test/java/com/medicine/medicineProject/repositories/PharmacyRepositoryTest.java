//package com.medicine.medicineProject.repositories;
//
//import com.medicine.medicineProject.models.DataConfig;
//import com.medicine.medicineProject.models.Address;
//import com.medicine.medicineProject.models.Pharmacy;
//import lombok.extern.slf4j.Slf4j;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest(classes = DataConfig.class)
//@ActiveProfiles("test")
//@Slf4j
//class PharmacyRepositoryTest {
//
//    @Autowired
//    PharmacyRepository pharmacyRepository;
//
//    @Autowired
////    AddressRepository addressRepository;
//
//    Pharmacy pharmacy;
//
//
//
//    @BeforeEach
//    void setUp() {
//        pharmacy = new Pharmacy();
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    @DisplayName("Test that pharmacy object is successfully saved to database")
//    void pharmacyIsSavedToDatabase(){
//        pharmacy.setPharmacyName("Precious pharmacy");
//        pharmacy.setPhoneNumber("09056784345");
//        pharmacy.setEmail("precious@hotmail.com");
//        pharmacy.setPassword("password");
//        pharmacy.setId("1");
//
//        Pharmacy savedPharmacy = pharmacyRepository.save(pharmacy);
//
//        log.info("Pharmacy after saving --> {}", savedPharmacy);
//        assertNotNull(savedPharmacy);
//    }
//
//    @Test
//    @DisplayName("Test that pharmacy details can be updated in database")
//    void detailsCanBeUpdated(){
//        pharmacy.setPharmacyName("Precious pharmacy");
//        pharmacy.setPhoneNumber("09056784345");
//        pharmacy.setEmail("precious@hotmail.com");
//        pharmacy.setPassword("password");
//        pharmacy.setId("5");
//
//      Pharmacy pharm =  pharmacyRepository.save(pharmacy);
//      log.info("Pharmacy object before update --> {}", pharm);
//
//        pharm.setPharmacyName("Another name");
//
//        pharmacyRepository.save(pharm);
//        log.info("Pharmacy object after update --> {}", pharm);
//
//        Assertions.assertThat(pharmacy.getPharmacyName()).isEqualTo("Another name");
//    }
//
//    @Test
//    void testThatICanFindPharmacyById(){
//        Optional<Pharmacy> pharmacy = pharmacyRepository.findById("5");
//        Assertions.assertThat(pharmacy.get()).isNotNull();
//    }
//
//    @Test
//    void testThatPharmacyWillHaveAddress(){
//        Address address = new Address();
//        address.setId("2");
//        address.setCity("Lagos");
//        address.setState("Lagos");
//        address.setLandmark("Church");
//        address.setStreetName("Herbert Macaulay");
//        address.setStreetNumber("312");
//
//        addressRepository.save(address);
//
//        pharmacy.setPharmacyName("New pharmacy");
//        pharmacy.setId("3");
//        pharmacy.setAddress(address);
//
//        pharmacyRepository.save(pharmacy);
//
//        Assertions.assertThat(pharmacy.getAddress()).isNotNull();
//    }
//
//    @Test
//    void testThatPharmacyCanBeRemovedFromDatabase(){
//        pharmacyRepository.deleteById("3");
//        Optional<Pharmacy> pharm = pharmacyRepository.findById("3");
//        Assertions.assertThat(pharmacyRepository.existsById("3")).isFalse();
//    }
//
//
//}