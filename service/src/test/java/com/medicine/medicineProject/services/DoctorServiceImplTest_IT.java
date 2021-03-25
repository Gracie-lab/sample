package com.medicine.medicineProject.services;

import com.medicine.medicineProject.models.DataConfig;
import com.medicine.medicineProject.dtos.DoctorUpdateDto;
import com.medicine.medicineProject.dtos.UpdateDtoMapper;
import com.medicine.medicineProject.models.Doctor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = DataConfig.class)
@ActiveProfiles("test")
public class DoctorServiceImplTest_IT {

    @Test
    void testThatDoctorDetailsCanBeUpdated() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Doctor existingDoctor = new Doctor();
        existingDoctor.setFirstName("Joel");
        existingDoctor.setEmail("joe@gmail.com");
        existingDoctor.setPassword("joe");

        DoctorUpdateDto doctorUpdateDto = new DoctorUpdateDto();
        doctorUpdateDto.setFirstName("John");
        doctorUpdateDto.setLastName("Philips");

        UpdateDtoMapper.updateDoctorDetailsWith(doctorUpdateDto,existingDoctor);

        assertThat(existingDoctor.getFirstName()).isEqualTo("John");
        assertThat(existingDoctor.getLastName()).isEqualTo("Philips");

    }
}
