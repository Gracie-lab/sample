package com.medicine.medicineProject.services;

import com.medicine.medicineProject.dtos.AdminUpdateDto;
import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;
import com.medicine.medicineProject.exceptions.UserDoesNotExistException;
import com.medicine.medicineProject.models.Admin;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public interface AdminService {

    Optional<Admin>findAdminByEmail(String email);
    Optional<Admin>findAdminById(String id);
    Admin save (Admin admin);
    Admin register(RegisterDto registerDto) throws RegisterException, InvalidInputException;
    Optional<Admin>finAdminByPhoneNumber(String phoneNumber);

    Admin updateProfile(AdminUpdateDto adminUpdateDto, String adminId) throws  IllegalAccessException, NoSuchMethodException, InvocationTargetException, UserDoesNotExistException;
}
