package com.medicine.medicineProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableAsync
@EnableMongoRepositories(basePackages = {"com.medicine.medicineProject.*"})
@EntityScan(basePackages = {"com.medicine.medicineProject.*"})
@SpringBootApplication(scanBasePackages = {"com.medicine.medicineProject"})
@ComponentScan(basePackages = {"com.medicine.medicineProject.*"})
@ComponentScan(basePackages = {"com.medicine.*"})
public class MedicineProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicineProjectApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer webMvcConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/home")
						.allowedOrigins("https://medicinecom.netlify.app")
				.allowedMethods("POST", "GET", "DELETE", "PATCH", "PUT");
			}
		};
	}
}
