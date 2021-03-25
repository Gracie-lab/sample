package com.medicine.medicineProject.services;

import com.medicine.medicineProject.models.DataConfig;
import com.medicine.medicineProject.dtos.PharmacyUpdateDto;
import com.medicine.medicineProject.dtos.UpdateDtoMapper;
import com.medicine.medicineProject.exceptions.UserDoesNotExistException;
import com.medicine.medicineProject.models.Pharmacy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DataConfig.class)
class PharmacyServiceImplTestInt {

    @Autowired
    PharmacyServiceImpl pharmacyService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void pharmacyCanUpdateProfile() throws IllegalAccessException, NoSuchMethodException,
            InvocationTargetException, UserDoesNotExistException {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId("30");
        pharmacy.setPhoneNumber("09087456785");
        pharmacy.setEmail("mypharm@gmail.com");
        pharmacy.setPassword("pass");
        pharmacyService.save(pharmacy);
        assertThat(pharmacy.getId()).isNotNull();

        log.info("Phone number before update -> {}", pharmacy.getPhoneNumber());

        PharmacyUpdateDto pharmacyUpdateDto = new PharmacyUpdateDto();
        pharmacyUpdateDto.setPhoneNumber("07066176828");
        UpdateDtoMapper.updatePharmacyDetailsWith(pharmacyUpdateDto, pharmacy);
//        pharmacyService.updateProfile(pharmacyUpdateDto, "30");
        log.info("Phone number after update -> {}", pharmacy.getPhoneNumber());

        assertThat(pharmacy.getPhoneNumber()).isEqualTo("07066176828");

    }
}