package com.medicine.medicineProject.services;

import com.medicine.medicineProject.dtos.PharmacyUpdateDto;
import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.dtos.RegisterDtoMapper;
import com.medicine.medicineProject.dtos.UpdateDtoMapper;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;
import com.medicine.medicineProject.exceptions.UpdateUserException;
import com.medicine.medicineProject.exceptions.UserDoesNotExistException;
import com.medicine.medicineProject.models.Pharmacy;
import com.medicine.medicineProject.repositories.PharmacyRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import static org.mockito.Mockito.*;

@Slf4j
class PharmacyServiceImplTest {
    @Mock
    PharmacyRepository pharmacyRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    PharmacyServiceImpl pharmacyService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName(("Test that I can find pharmacy by email"))
    void findByEmailTest(){
        when(pharmacyRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        pharmacyService.findPharmacyByEmail(anyString());
        verify(pharmacyRepository, times(1)).findByEmail(anyString());
    }
    @Test
    void pharmacyObjectIsSavedWhenRegisterServiceIsCalled() throws InvalidInputException, RegisterException {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("precious@gmail.com");
        registerDto.setPassword("password");


        Pharmacy pharmacy = RegisterDtoMapper.createPharmacyFrom(registerDto);
        pharmacy.setPassword(passwordEncoder.encode(pharmacy.getPassword()));

        when(pharmacyRepository.findByEmail("precious@gmail.com")).thenReturn(Optional.empty());
        when(pharmacyRepository.save(pharmacy)).thenReturn(pharmacy);
        pharmacyService.register(registerDto);

        verify(pharmacyRepository, times(1)).save(pharmacy);

    }

//    @Test
//    void exceptionIsThrownWhenEmailIsNotValid(){
//        RegisterDto pharmacyDto = new RegisterDto();
//        pharmacyDto.setEmail("pharmacy1");
//        pharmacyDto.setPassword("password");
//
//        Pharmacy newPharmacy = RegisterDtoMapper.createPharmacyFrom(pharmacyDto);
//
//        when(pharmacyRepository.findByEmail("pharmacy")).thenReturn(Optional.empty());
//        when(pharmacyRepository.save(newPharmacy)).thenReturn(newPharmacy);
//
//        Assertions.assertThrows(InvalidInputException.class, () -> pharmacyService.register(pharmacyDto));
//
//    }

    @Test
    void pharmacyCanUpdateProfile() throws IllegalAccessException, NoSuchMethodException,
            InvocationTargetException, UserDoesNotExistException, UpdateUserException {
        PharmacyUpdateDto pharmacyUpdateDto = new PharmacyUpdateDto();
        pharmacyUpdateDto.setFirstNameOfContactPerson("pharm");

        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId("myid");

        Pharmacy updatedPharmacy = UpdateDtoMapper.updatePharmacyDetailsWith(pharmacyUpdateDto,pharmacy);


        when(pharmacyRepository.findById("myid")).thenReturn(Optional.of(pharmacy));
        when(pharmacyService.updateProfile(pharmacyUpdateDto, "myid")).thenReturn(updatedPharmacy);
        when(pharmacyService.save(updatedPharmacy)).thenReturn(updatedPharmacy);
        pharmacyService.updateProfile(pharmacyUpdateDto, "myid");
        verify(pharmacyRepository, times(2)).findById("myid");

    }
}