//package com.medicine.medicineProject.services;
//
//
//import com.medicine.medicineProject.dtos.RegisterDto;
//import com.medicine.medicineProject.dtos.RegisterDtoMapper;
//import com.medicine.medicineProject.exceptions.InvalidInputException;
//import com.medicine.medicineProject.exceptions.RegisterException;
//import com.medicine.medicineProject.models.Admin;
//import com.medicine.medicineProject.repositories.AdminRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//@Slf4j
//public class AdminServiceImplTest {
//    @Mock
//    AdminRepository adminRepository;
//
//    @Mock
//    PasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    AdminServiceImpl adminService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @AfterEach
//    void tearDown(){
//
//    }
//
//    @Test
//    @DisplayName("Test to find Admin by email")
//    void findByEmailTest(){
//        when(adminRepository.findByEmail(anyString())).thenReturn(Optional.empty());
//
//        adminService.findAdminByEmail(anyString());
//        verify(adminRepository, times(1)).findByEmail(anyString());
//
//    }
//
//    @Test
//    void adminObjectIsSavedWhenRegisterServiceIsCalled() throws RegisterException, InvalidInputException{
//
//        RegisterDto registerDto = new RegisterDto();
//        registerDto.setEmail("jon@gmail.com");
//        registerDto.setPassword("password");
//
//        Admin admin = RegisterDtoMapper.createAdminFrom(registerDto);
//        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
//
//        when(adminRepository.findByEmail("jon@gmail.com")).thenReturn(Optional.empty());
//        when(adminRepository.save(admin)).thenReturn(admin);
////        adminService.register(registerDto);
//
//        verify(adminRepository, times(1)).save(admin);
//    }
//}
