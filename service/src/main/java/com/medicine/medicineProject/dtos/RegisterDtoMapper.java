package com.medicine.medicineProject.dtos;


import com.medicine.medicineProject.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterDtoMapper {
    public static Patient createPatientFrom(RegisterDto registerDto){
        Patient newPatient = new Patient();
        if(registerDto.getEmail() != null){
            newPatient.setUsername(registerDto.getEmail());
        }else{
            newPatient.setUsername(registerDto.getPhoneNumber());
        }
        newPatient.setEmail(registerDto.getEmail());
        newPatient.setPassword(registerDto.getPassword());
        newPatient.setPhoneNumber(registerDto.getPhoneNumber());
//        new.setRole(Role.CUSTOMER);
        return newPatient;
    }

    public static Pharmacy createPharmacyFrom(RegisterDto registerDto){
        List<Role> roles = new ArrayList<>();
        Pharmacy newPharmacy = new Pharmacy();
        newPharmacy.setUsername(registerDto.getEmail());
        newPharmacy.setEmail(registerDto.getEmail());
        newPharmacy.setPassword(registerDto.getPassword());
        newPharmacy.setRole(Role.PHARMACY);
        newPharmacy.setRoles(roles);
        roles.add(newPharmacy.getRole());
        return newPharmacy;
    }

    public static Doctor createDoctorFrom(RegisterDto doctorDto) {
        List<Role> roles = new ArrayList<>();
        Doctor newDoctor = new Doctor();
        newDoctor.setUsername(doctorDto.getEmail());
        newDoctor.setEmail(doctorDto.getEmail());
        newDoctor.setPassword(doctorDto.getPassword());
        newDoctor.setRole(Role.DOCTOR);
        newDoctor.setRoles(roles);
        roles.add(newDoctor.getRole());
        return newDoctor;
    }


    public static Hospital createHospitalFrom(RegisterDto registerDto) {
        List<Role> roles = new ArrayList<>();
        Hospital newHospital = new Hospital();
        newHospital.setUsername(registerDto.getEmail());
        newHospital.setEmail(registerDto.getEmail());
        newHospital.setPassword(registerDto.getPassword());
        newHospital.setRole(Role.HOSPITAL);
        newHospital.setRoles(roles);
        roles.add(newHospital.getRole());
        return newHospital;
    }

    public static Admin createAdminFrom(RegisterDto registerDto){
        return null;
    }
}
