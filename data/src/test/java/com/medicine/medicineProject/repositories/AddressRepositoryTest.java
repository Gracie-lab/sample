//package com.medicine.medicineProject.repositories;
//
//import com.medicine.medicineProject.models.DataConfig;
//import com.medicine.medicineProject.models.Address;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Profile;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(classes = DataConfig.class)
//@ActiveProfiles("test")
//@Slf4j
//class AddressRepositoryTest {
//
//    @Autowired
//    AddressRepository addressRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @BeforeEach
//    void setUp() {
//
//    }
//
//    @Test
//    @DisplayName("Address is saved to database")
//    void addressCanBeSavedTest(){
//        Address address = new Address();
//        address.setStreetNumber("10");
//        address.setCity("Lekki");
//        address.setState("Lagos");
//        address.setStreetName("Adedeji Kareem");
//        address.setLandmark("library");
//        address.setId("1");
//
//
//        log.info("Address instance --> {}", address);
//
//        Address address1 = a.save(address);
//
//        assertThat(address1).isNotNull();
//    }
//
//    @Test
//    @DisplayName("Find all addresses in database")
//    void findAllAddressesTest(){
//        Address address1 = new Address();
//        address1.setStreetNumber("11");
//        address1.setCity("Yaba");
//        address1.setState("Lagos");
//        address1.setStreetName("Alagomeji");
//        address1.setLandmark("Mosque");
//        address1.setId("4");
//        addressRepository.save(address1);
//
//        log.info("{}", address1.getId());
//        List<Address> addressList = addressRepository.findAll();
//
//        log.info(" -> {}", addressList.size());
//        assertThat(addressList.size()).isEqualTo(3);
//    }
//
//    @Test
//    @DisplayName("We can find address by id")
//    void findAddressByIdTest(){
//        Optional<Address> savedAddress = addressRepository.findById("1");
//
//        assertThat(savedAddress.get().getId()).isEqualTo("1");
//    }
//
//    @Test
//    @DisplayName("Address in database can be updated")
//    void updateAddressDetailsTest(){
//        Optional<Address> savedAddress = addressRepository.findById("1");
//        log.info("savedAddress state is --> {}", savedAddress.get().getState());
//        assertThat(savedAddress.get().getState()).isEqualTo("Lagos");
//
//        savedAddress.get().setState("Ondo");
//        addressRepository.save(savedAddress.get());
//        log.info("savedAddress state after update is -->{}", savedAddress.get().getState());
//        assertThat(savedAddress.get().getState()).isEqualTo("Ondo");
//    }
//
//    @Test
//    @DisplayName("Address can be deleted by id")
//    void deleteAddressByIdTest(){
//        assertThat(addressRepository.existsById("2")).isTrue();
//
//        addressRepository.deleteById("2");
//
//        assertThat(addressRepository.existsById("2")).isFalse();
//    }
//}