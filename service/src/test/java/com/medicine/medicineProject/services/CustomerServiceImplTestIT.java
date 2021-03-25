//package com.medicine.medicineProject.services;
//
//import com.medicine.medicineProject.dtos.RegisterDto;
//import com.medicine.medicineProject.exceptions.EmailException;
//import com.medicine.medicineProject.exceptions.RegisterException;
//import com.medicine.medicineProject.models.Customer;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@Service
//class CustomerServiceImplTestIT {
//
//    @Autowired
//    CustomerService customerService;
//
//    @Autowired
//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void testRegisterService() throws EmailException, RegisterException {
//        RegisterDto customerDto = new RegisterDto();
//        customerDto.setEmail("custome");
//        customerDto.setPassword("pass");
//       Optional<Customer> savedCustomer = customerService.register(customerDto);
//
//       assertNotNull(savedCustomer);
//    }
//}