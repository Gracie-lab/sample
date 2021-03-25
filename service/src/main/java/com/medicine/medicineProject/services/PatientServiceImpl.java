package com.medicine.medicineProject.services;

import com.medicine.medicineProject.dtos.PatientUpdateDto;
import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.dtos.RegisterDtoMapper;
import com.medicine.medicineProject.dtos.UpdateDtoMapper;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;
import com.medicine.medicineProject.exceptions.UpdateUserException;
import com.medicine.medicineProject.models.Patient;
import com.medicine.medicineProject.models.Role;
import com.medicine.medicineProject.repositories.PatientRepository;
import com.medicine.medicineProject.service.UserPrincipalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Valid
public class PatientServiceImpl implements PatientService {

    @Autowired
    private UserPrincipalService userPrincipalService;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    RegisterDtoMapper registerDtoMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Optional<Patient> findPatientByEmail(String email) {

        return patientRepository.findByEmail(email);
    }

    @Override
    public Optional<Patient> findPatientByPhoneNumber(String phoneNumber) {
        return patientRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Optional<Patient> findPatientById(String patientId) {
        return patientRepository.findById(patientId);
    }

//    @Override
//    public Patient updateProfile(PatientUpdateDto patientUpdateDto, String userToken, String patientId) throws UpdateUserException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        Optional<Patient> optionalPatient = findPatientByEmail(userToken);
//
////        Optional<Patient> optionalPatient = findPatientById(patientId);
//
//        if (optionalPatient.isEmpty()) {
//            throw new UpdateUserException("This user does not exist");
//        } else {
//            Patient updatedPatient = UpdateDtoMapper.updatePatientDetailsWith(patientUpdateDto, optionalPatient.get());
//            return save(updatedPatient);
//        }
//
//    }

    @Override
    public Patient updateProfile(PatientUpdateDto patientUpdateDto, String userToken) throws UpdateUserException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Optional<Patient> optionalPatient = findPatientByEmail(userToken);

//        Optional<Patient> optionalPatient = findPatientById(patientId);

        if (optionalPatient.isEmpty()) {
            throw new UpdateUserException("This user does not exist");
        } else {
            Patient updatedPatient = UpdateDtoMapper.updatePatientDetailsWith(patientUpdateDto, optionalPatient.get());
            return save(updatedPatient);
        }

    }


    @Override
    public Patient save(Patient patient) {

        return patientRepository.save(patient);
    }

    private Optional<Patient> checkIfUserExistsBeforeRegistration(RegisterDto registerDto) throws InvalidInputException {
        Optional<Patient> optionalCustomer;
        if(registerDto.getEmail() != null) {
            optionalCustomer = findPatientByEmail(registerDto.getEmail());
        }
        else if(registerDto.getPhoneNumber() != null){
            optionalCustomer = findPatientByPhoneNumber(registerDto.getPhoneNumber());
        }
        else{
            throw new InvalidInputException("Provide email or phone number");
        }
        return optionalCustomer;
    }

    @Override
    public ResponseEntity<?> register(RegisterDto registerDto) throws RegisterException, InvalidInputException {
       Optional<Patient> optionalCustomer = checkIfUserExistsBeforeRegistration(registerDto);
        if(optionalCustomer.isPresent()){
            throw new RegisterException("Account with given credentials exists");
        } else{
             registerCustomer(registerDto);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    private Patient registerCustomer(RegisterDto registerDto) {
        Patient newPatient = RegisterDtoMapper.createPatientFrom(registerDto);
        newPatient.setRole(Role.CUSTOMER);
        List<Role> roles = new ArrayList<>();
        roles.add(Role.CUSTOMER);
        newPatient.setRoles(roles);;
        newPatient.setPassword(passwordEncoder.encode(newPatient.getPassword()));

        userService.saveUser(newPatient);
        log.info("hashed password after sign up -> {}", newPatient.getPassword());
        return save(newPatient);
    }

}
