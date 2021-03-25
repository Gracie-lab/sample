package com.medicine.medicineProject.services;

import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.dtos.RegisterDtoMapper;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;
import com.medicine.medicineProject.models.Patient;
import com.medicine.medicineProject.repositories.PatientRepository;
import com.medicine.medicineProject.service.UserPrincipalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;

@Slf4j
class PatientServiceImplTest {

    @Mock
    PatientRepository patientRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserPrincipalService userPrincipalService;

    @InjectMocks
    PatientServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test that I can find user by email")
    void testFindUserByEmail(){
        when(patientRepository.findByEmail(anyString())).thenReturn(null);

       customerService.findPatientByEmail(anyString());
       verify(patientRepository, times(1)).findByEmail(anyString());
    }

    @Test
    void testRegisterService() throws RegisterException, InvalidInputException {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("gracie@gmail.com");
        registerDto.setPassword("password");

        Patient newPatient = RegisterDtoMapper.createPatientFrom(registerDto);
        newPatient.setPassword(passwordEncoder.encode("password"));

        when(patientRepository.findByEmail("gracie@gmail.com"))
                .thenReturn(Optional.empty());
        when(patientRepository.save(newPatient)).thenReturn(newPatient);
        customerService.register(registerDto);

        verify(patientRepository, times(1)).save(newPatient);
    }

    @Test
    void testThatExceptionIsThrownWhenEMailIsNotValid() throws InvalidInputException, RegisterException {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setEmail("email");

        registerDto.setPassword("myPassword");

        Patient patient = RegisterDtoMapper.createPatientFrom(registerDto);

        when(patientRepository.findByEmail("email")).thenReturn(Optional.empty());
        when(patientRepository.save(patient)).thenReturn(patient);

        Assertions.assertThrows(InvalidInputException.class, ()-> customerService.register(registerDto));
    }


    @Test
//    @DisplayName("")
    void testThatExceptionIsThrownWhenPhoneNumberIsInvalid(){
        RegisterDto registerDto = new RegisterDto();
        registerDto.setPhoneNumber("0815642");
        registerDto.setPassword("password");

        Patient patient = RegisterDtoMapper.createPatientFrom(registerDto);

        when(patientRepository.findByPhoneNumber("0815642")).thenReturn(Optional.empty());
        when(patientRepository.save(patient)).thenReturn(patient);

        Assertions.assertThrows(InvalidInputException.class, ()-> customerService.register(registerDto));
    }


}