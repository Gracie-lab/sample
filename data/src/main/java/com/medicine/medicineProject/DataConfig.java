package com.medicine.medicineProject;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan(basePackages = "com.medicine.medicineProject")
@EntityScan(basePackages = "com.medicine.medicineProject")
@Configuration
@EnableMongoRepositories
@EnableAutoConfiguration
public class DataConfig {
}
