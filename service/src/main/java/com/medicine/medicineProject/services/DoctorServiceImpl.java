package com.medicine.medicineProject.services;

import com.medicine.medicineProject.dtos.DoctorUpdateDto;
import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.dtos.RegisterDtoMapper;
import com.medicine.medicineProject.dtos.UpdateDtoMapper;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;

//import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.UpdateUserException;
import com.medicine.medicineProject.exceptions.UserDoesNotExistException;
import com.medicine.medicineProject.models.Doctor;
import com.medicine.medicineProject.repositories.DoctorRepository;
import com.medicine.medicineProject.utils.MailValidator;
import com.medicine.medicineProject.service.UserPrincipalService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
@Data
@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    RegisterDtoMapper registerDtoMapper;

    @Autowired
    UserService userService;

    @Autowired
    private MailValidator mailValidator;

    @Autowired
    private UserPrincipalService userPrincipalService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Optional<Doctor> findDoctorByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }

    @Override
    public Optional<Doctor> findDoctorById(String id) {
        return doctorRepository.findById(id);
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor register(RegisterDto registerDto) throws RegisterException, InvalidInputException {
        Optional<Doctor> optionalDoctor = findDoctorByEmail(registerDto.getEmail());
        if (optionalDoctor.isPresent()) {
            throw new RegisterException("Account with this email address already exist");
        } else {
            return registerDoctor(registerDto);
        }
    }

    @Override
    public Doctor updateProfile(DoctorUpdateDto doctorUpdateDto, String doctorId) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, UserDoesNotExistException, UpdateUserException {
        Optional<Doctor> optionalDoctor = findDoctorById(doctorId);

        if (optionalDoctor.isEmpty()) {
            throw new UpdateUserException("This user does not exist");
        } else {
            Doctor updatedDoctor = UpdateDtoMapper.updateDoctorDetailsWith(doctorUpdateDto, optionalDoctor.get());
            return save(updatedDoctor);
        }
    }


    private Doctor registerDoctor(RegisterDto registerDto){
            Doctor newDoctor = RegisterDtoMapper.createDoctorFrom(registerDto);
            newDoctor.setPassword((passwordEncoder.encode(newDoctor.getPassword())));
            userService.saveUser(newDoctor);
            return save(newDoctor);
        }
    }