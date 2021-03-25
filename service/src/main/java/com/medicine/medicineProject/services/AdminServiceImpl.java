package com.medicine.medicineProject.services;


import com.medicine.medicineProject.dtos.AdminUpdateDto;
import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.dtos.RegisterDtoMapper;
import com.medicine.medicineProject.dtos.UpdateDtoMapper;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;
import com.medicine.medicineProject.exceptions.UserDoesNotExistException;
import com.medicine.medicineProject.models.Admin;
import com.medicine.medicineProject.repositories.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
@Slf4j
@Valid
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    RegisterDtoMapper registerDtoMapper;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Optional<Admin> findAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    @Override
    public Optional<Admin> findAdminById(String id) {
        return adminRepository.findById(id);
    }

    @Override
    public Optional<Admin> finAdminByPhoneNumber(String phoneNumber) {
        return adminRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Admin updateProfile(AdminUpdateDto adminUpdateDto, String adminId) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, UserDoesNotExistException {
        Optional<Admin>optionalAdmin=findAdminById(adminId);

        if (optionalAdmin.isEmpty()){
            throw new UserDoesNotExistException("This user does not exist");
        }else {
            Admin updatedAdmin = UpdateDtoMapper.updateAdminDetailsWith(adminUpdateDto, optionalAdmin.get());
            return save(updatedAdmin);
        }
    }

    @Override
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin register(RegisterDto registerDto) throws RegisterException, InvalidInputException {
        Optional<Admin> optionalAdmin = findAdminByEmail(registerDto.getEmail());
        if (optionalAdmin.isPresent()) {
            throw new RegisterException("Account with this email exist already");
        }else{
           return registerAdmin(registerDto);
        }
    }

    private Admin registerAdmin(RegisterDto registerDto) {
        Admin newAdmin = RegisterDtoMapper.createAdminFrom(registerDto);
        newAdmin.setPassword(passwordEncoder.encode(newAdmin.getPassword()));
        return save(newAdmin);
    }


}
