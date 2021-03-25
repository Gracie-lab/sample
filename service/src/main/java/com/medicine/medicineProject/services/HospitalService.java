package com.medicine.medicineProject.services;

import com.medicine.medicineProject.dtos.HospitalUpdateDto;
import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;
import com.medicine.medicineProject.exceptions.UpdateUserException;
import com.medicine.medicineProject.exceptions.UserDoesNotExistException;
import com.medicine.medicineProject.models.Hospital;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
public interface HospitalService {

    Optional<Hospital> findHospitalByEmail(String email);
    Hospital save (Hospital hospital);

    Hospital register(RegisterDto registerDto) throws RegisterException, InvalidInputException;
    Hospital updateProfile(HospitalUpdateDto hospitalUpdateDto, String hospitalId) throws UserDoesNotExistException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, UpdateUserException;
    Optional<Hospital> findHospitalById(String id);

}
