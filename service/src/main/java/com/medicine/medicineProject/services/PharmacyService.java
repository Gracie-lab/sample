package com.medicine.medicineProject.services;

import com.medicine.medicineProject.dtos.PharmacyUpdateDto;
import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;
import com.medicine.medicineProject.exceptions.UpdateUserException;
import com.medicine.medicineProject.exceptions.UserDoesNotExistException;
import com.medicine.medicineProject.models.Pharmacy;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public interface PharmacyService {
    Optional<Pharmacy> findPharmacyByEmail(String email);
    Pharmacy save (Pharmacy pharmacy);
    Pharmacy register (RegisterDto registerDto) throws RegisterException, InvalidInputException;
    Pharmacy updateProfile(PharmacyUpdateDto pharmacyUpdateDto, String pharmacyId) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, UpdateUserException, UserDoesNotExistException;
    Optional<Pharmacy> findPharmacyById(String id);


}
