package com.medicine.medicineProject.services;

import com.medicine.medicineProject.dtos.PatientUpdateDto;
import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;
import com.medicine.medicineProject.exceptions.UpdateUserException;
import com.medicine.medicineProject.models.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
public interface PatientService {

    Optional<Patient> findPatientByEmail(String email);
    Patient save (Patient patient);

   ResponseEntity<?> register(RegisterDto registerDto) throws RegisterException, InvalidInputException;

   Optional<Patient> findPatientByPhoneNumber(String phoneNumber);

   Optional<Patient> findPatientById(String patientId);

//   Patient updateProfile(PatientUpdateDto patientUpdateDto, String userToken, String patientId) throws UpdateUserException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;

    Patient updateProfile(PatientUpdateDto patientUpdateDto, String userToken) throws UpdateUserException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;

}
