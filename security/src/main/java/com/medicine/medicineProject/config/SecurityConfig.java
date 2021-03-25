package com.medicine.medicineProject.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:securityConstants.properties")
@Configuration
@ComponentScan(basePackages = {"com.medicine.medicineProject.**"})
@EntityScan(basePackages = "com.medicine.medicineProject")
@EnableAutoConfiguration
public class SecurityConfig {

}
