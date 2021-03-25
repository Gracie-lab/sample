package com.medicine.medicineProject.services;

import com.medicine.medicineProject.dtos.RegisterDtoMapper;
import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;
import com.medicine.medicineProject.models.Hospital;
import com.medicine.medicineProject.repositories.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
//import org.mockito.stubbing.OngoingStubbing;
import org.springframework.security.crypto.password.PasswordEncoder;

//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.springframework.data.mongodb.core.aggregation.ConditionalOperators.Cond.when;
//import static sun.awt.util.PerformanceLogger.times;
import java.util.Optional;

@Slf4j
public class HospitalServiceImplTest {

    @Mock
    HospitalRepository hospitalRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    HospitalServiceImpl hospitalService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown(){

    }

    @Test
    @DisplayName("Test that I can find hospital by email")
    void findByEmailTest(){
        when(hospitalRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        hospitalService.findHospitalByEmail(anyString());
        verify(hospitalRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void hospitalObjectIsSavedWhenRegisterServiceIsCalled()throws RegisterException, InvalidInputException {

        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("meteouzoma@gmail.com");
        registerDto.setPassword("password");

        Hospital hospital = RegisterDtoMapper.createHospitalFrom(registerDto);
        hospital.setPassword(passwordEncoder.encode(hospital.getPassword()));


        when(hospitalRepository.findByEmail("meteouzoma@gmail.com")).thenReturn(Optional.empty());
        when(hospitalRepository.save(hospital)).thenReturn(hospital);
        hospitalService.register(registerDto);

        verify(hospitalRepository, times(1)).save(hospital);
    }


}
