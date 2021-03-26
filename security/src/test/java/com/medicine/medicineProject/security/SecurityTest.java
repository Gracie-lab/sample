//package com.medicine.medicineProject.security;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
////@SpringBootTest(classes = SecurityConfig.class)
//@RunWith(SpringRunner.class)
//@WebAppConfiguration
//class SecurityTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    WebApplicationContext webApplicationContext;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .build();
//
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//}