package com.medicine.medicineProject.services;

import com.medicine.medicineProject.dtos.PharmacyUpdateDto;
import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.dtos.RegisterDtoMapper;
import com.medicine.medicineProject.dtos.UpdateDtoMapper;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;
import com.medicine.medicineProject.exceptions.UpdateUserException;
import com.medicine.medicineProject.exceptions.UserDoesNotExistException;
import com.medicine.medicineProject.repositories.PharmacyRepository;
import com.medicine.medicineProject.utils.MailValidator;
import com.medicine.medicineProject.models.Pharmacy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;


@Data
@Service

public class PharmacyServiceImpl implements PharmacyService{

    @Autowired
    PharmacyRepository pharmacyRepository;

    @Autowired
    RegisterDtoMapper registerDtoMapper;

    @Autowired
    private MailValidator mailValidator;

    @Autowired
    private UserService userService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    @Override
    public Optional<Pharmacy> findPharmacyByEmail(String email) {

        return pharmacyRepository.findByEmail(email);
    }

    @Override
    public Pharmacy save(Pharmacy pharmacy) {

        return pharmacyRepository.save(pharmacy);
    }

    @Override
    public Pharmacy register(RegisterDto registerDto) throws RegisterException, InvalidInputException {
//        if(!MailValidator.validateEmail(registerDto.getEmail())){
//            throw new InvalidInputException("Invalid Email");
//        }
        Optional<Pharmacy> optionalPharmacy = findPharmacyByEmail(registerDto.getEmail());

        if(optionalPharmacy.isPresent()){
            throw new RegisterException("Account with this email address exists");
        } else{
            return registerPharmacy(registerDto);
        }

    }

    @Override
    public Pharmacy updateProfile(PharmacyUpdateDto pharmacyUpdateDto, String pharmacyId) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, UpdateUserException, UserDoesNotExistException {
        Optional<Pharmacy> optionalPharmacy = findPharmacyById(pharmacyId);

        if (optionalPharmacy.isEmpty()) {
            throw new UpdateUserException("This user does not exist");
        } else {
            Pharmacy updatedPharmacy = UpdateDtoMapper.updatePharmacyDetailsWith(pharmacyUpdateDto, optionalPharmacy.get());
            return save(updatedPharmacy);
        }
    }

    @Override
    public Optional<Pharmacy> findPharmacyById(String id) {
        return pharmacyRepository.findById(id);
    }

    private Pharmacy registerPharmacy(RegisterDto registerDto) {
        Pharmacy newPharmacy = RegisterDtoMapper.createPharmacyFrom(registerDto);
        newPharmacy.setPassword(passwordEncoder.encode(newPharmacy.getPassword()));
        userService.saveUser(newPharmacy);
        return save(newPharmacy);
    }

}
