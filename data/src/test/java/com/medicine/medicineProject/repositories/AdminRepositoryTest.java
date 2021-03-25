package com.medicine.medicineProject.repositories;


import com.medicine.medicineProject.models.DataConfig;
import com.medicine.medicineProject.models.Address;
import com.medicine.medicineProject.models.Admin;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = DataConfig.class)
@ActiveProfiles("test")
@Slf4j
class AdminRepositoryTest {
    @Autowired
    AdminRepository adminRepository;

    @BeforeEach
    void setUp(){

    }

    @AfterEach
    void tearDown(){

    }
    @Test
    void testThatAdminCanBeSaved(){
        Admin admin = new Admin();
        admin.setId("1");
        admin.setFirstName("Fashola");
        admin.setLastName("Dangote");
        admin.setPassword("password");
        admin.setEmail("fashola@gmail.com");

        Admin newAdmin = adminRepository.save(admin);

        assertThat(newAdmin).isNotNull();
    }

    @Test
    @DisplayName("This test case tests that an address can be mapped to an Admin")
    void adminHasAddress(){
        Address address = new Address();
        address.setStreetNumber("100");
        address.setStreetName("yaba along");
        address.setId("1");
        Admin admin = new Admin();
        admin.setId("4");
        admin.setAddress(address);
        Admin newAdmin = adminRepository.save(admin);

        assertThat(newAdmin.getAddress()).isNotNull();
        assertThat(newAdmin.getAddress().getStreetNumber()).isEqualTo("100");

    }

//    @Test
//    void adminDetailsCanBeUpdated(){
//        Admin admin = new Admin();
//        admin.setLastName("Onyeka");
//        admin.setFirstName("Pastor");
//        admin.setPassword("password");
//        Admin savedAdmin = adminRepository.save(admin);
//        savedAdmin.setPassword("password");
//        adminRepository.save(admin);
//
//        assertThat(savedAdmin.getPassword()).isEqualTo("password");
//    }

    @Test
    @DisplayName("Admin details in database can be updated")
    void updateAdminDetailsTest(){
        Optional<Admin> savedAdmin = adminRepository.findById("1");
        log.info("savedAdmin name is --> {}", savedAdmin.get().getFirstName());
        assertThat(savedAdmin.get().getFirstName()).isEqualTo("grace");

        savedAdmin.get().setFirstName("john");

        adminRepository.save(savedAdmin.get());
        log.info("savedAddress state after update is -->{}", savedAdmin.get().getFirstName());
        assertThat(savedAdmin.get().getFirstName()).isEqualTo("john");
    }


    @Test
    void adminCanBeRemovedFromDatabase(){
        adminRepository.deleteById("1");
        assertThat(adminRepository.existsById("1")).isFalse();
    }
}
