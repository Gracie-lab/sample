package com.medicine.medicineProject.services;

import com.medicine.medicineProject.dtos.DoctorUpdateDto;
import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;
import com.medicine.medicineProject.exceptions.UpdateUserException;
import com.medicine.medicineProject.exceptions.UserDoesNotExistException;
import com.medicine.medicineProject.models.Doctor;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public interface DoctorService {
    Optional<Doctor> findDoctorByEmail(String email);
    Optional<Doctor> findDoctorById(String id);
    Doctor save(Doctor doctor);

    Doctor register (RegisterDto registerDto) throws RegisterException, InvalidInputException;

    Doctor updateProfile(DoctorUpdateDto doctorUpdateDto, String doctorId) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, UserDoesNotExistException, UpdateUserException;
}
