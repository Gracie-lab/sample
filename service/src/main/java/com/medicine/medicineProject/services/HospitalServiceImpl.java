package com.medicine.medicineProject.services;

import com.medicine.medicineProject.dtos.HospitalUpdateDto;
import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.dtos.RegisterDtoMapper;
import com.medicine.medicineProject.dtos.UpdateDtoMapper;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;
import com.medicine.medicineProject.exceptions.UpdateUserException;
import com.medicine.medicineProject.exceptions.UserDoesNotExistException;
import com.medicine.medicineProject.models.Hospital;
import com.medicine.medicineProject.repositories.HospitalRepository;
import com.medicine.medicineProject.utils.MailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Data
@Component
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    RegisterDtoMapper registerDtoMapper;

    @Autowired
    private MailValidator mailValidator;

    @Autowired
    private UserService userService;


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public Optional<Hospital> findHospitalByEmail(String email) {
        return hospitalRepository.findByEmail(email);
    }

    @Override
    public Hospital save(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    @Override
    public Hospital register(RegisterDto registerDto) throws RegisterException, InvalidInputException {

            Optional<Hospital> optionalHospital = findHospitalByEmail(registerDto.getEmail());
            if (optionalHospital.isPresent()) {
                throw new RegisterException("Account with this email exist already");
            } else {
               return registerHospital(registerDto);
            }
    }

    @Override
    public Hospital updateProfile(HospitalUpdateDto hospitalUpdateDto, String hospitalId) throws UserDoesNotExistException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, UpdateUserException {
        Optional<Hospital> optionalHospital = findHospitalById(hospitalId);

        if (optionalHospital.isEmpty()) {
            throw new UpdateUserException("This user does not exist");
        } else {
            Hospital updatedHospital = UpdateDtoMapper.updateHospitalDetailsWith(hospitalUpdateDto, optionalHospital.get());
            return save(updatedHospital);
        }
    }

    @Override
    public Optional<Hospital> findHospitalById(String id) {
        return hospitalRepository.findById(id);
    }


    private Hospital registerHospital (RegisterDto registerDto){
            Hospital newHospital = RegisterDtoMapper.createHospitalFrom(registerDto);
            newHospital.setPassword(passwordEncoder.encode(newHospital.getPassword()));
            userService.saveUser(newHospital);
            return save(newHospital);
        }
    }
