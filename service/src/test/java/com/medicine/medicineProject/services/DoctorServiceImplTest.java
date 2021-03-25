package com.medicine.medicineProject.services;

import com.medicine.medicineProject.dtos.DoctorUpdateDto;
import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.dtos.RegisterDtoMapper;
import com.medicine.medicineProject.dtos.UpdateDtoMapper;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;
import com.medicine.medicineProject.exceptions.UpdateUserException;
import com.medicine.medicineProject.exceptions.UserDoesNotExistException;
import com.medicine.medicineProject.models.Doctor;
import com.medicine.medicineProject.repositories.DoctorRepository;
import com.medicine.medicineProject.service.UserPrincipalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@Slf4j
class DoctorServiceImplTest {
    @Mock
    DoctorRepository doctorRepository;

    @Mock
    UserPrincipalService userPrincipalService;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    DoctorServiceImpl doctorService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown(){

    }

    @Test
    @DisplayName("Test that I can find Doctor my email")
    void findByEmailTest(){
        when(doctorRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        doctorService.findDoctorByEmail(anyString());


        verify(doctorRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void doctorObjectIsSavedWhenRegisterServiceIsCalled() throws InvalidInputException, RegisterException {
        RegisterDto doctorDto = new RegisterDto();
        doctorDto.setEmail("gafaruoboirien@gmail.com");
        doctorDto.setPassword("password");

        Doctor newDoctor = RegisterDtoMapper.createDoctorFrom(doctorDto);
        newDoctor.setPassword(passwordEncoder.encode("password"));


        when(doctorRepository.findByEmail("gafaruoboirien@gmail.com")).thenReturn(Optional.empty());
        when(doctorRepository.save(newDoctor)).thenReturn(newDoctor);
        doctorService.register(doctorDto);

        verify(doctorRepository, times(1)).save(newDoctor);

    }

    @Test
    void testThatDoctorDetailsCanBeUpdated() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, UserDoesNotExistException, UpdateUserException {
        Doctor existingDoctor = new Doctor();
        existingDoctor.setFirstName("Simeon");
        existingDoctor.setEmail("sim@gmail.com");
        existingDoctor.setPassword("sims");

        DoctorUpdateDto doctorUpdateDto = new DoctorUpdateDto();
        doctorUpdateDto.setFirstName("");
        doctorUpdateDto.setLastName("Philips");

        Doctor updatedDoctor = UpdateDtoMapper.updateDoctorDetailsWith(doctorUpdateDto,existingDoctor);

        when(doctorRepository.findById("2")).thenReturn(Optional.of(existingDoctor));
        when(doctorService.updateProfile(doctorUpdateDto,"2")).thenReturn(updatedDoctor);
        when(doctorRepository.save(updatedDoctor)).thenReturn(updatedDoctor);

        doctorService.updateProfile(doctorUpdateDto,"2");

        verify(doctorRepository,times(1)).save(updatedDoctor);
    }



}
