package com.medicine.medicineProject.dtos;

import com.medicine.medicineProject.models.*;
import com.medicine.medicineProject.utils.UpdateObjectHelper;

import java.lang.reflect.InvocationTargetException;

public class UpdateDtoMapper {

    public static Pharmacy updatePharmacyDetailsWith(PharmacyUpdateDto pharmacyUpdateDto, Pharmacy pharmacy)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

       UpdateObjectHelper.updateUserProfile(pharmacyUpdateDto, pharmacy);
        return pharmacy;
    }

    public static Doctor updateDoctorDetailsWith(DoctorUpdateDto doctorUpdateDto, Doctor doctor)
            throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        UpdateObjectHelper.updateUserProfile(doctorUpdateDto,doctor);
        return doctor;
    }

    public static Patient updatePatientDetailsWith(PatientUpdateDto patientUpdateDto, Patient patient) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        UpdateObjectHelper.updateUserProfile(patientUpdateDto, patient);
        return patient;
    }

    public static Hospital updateHospitalDetailsWith(HospitalUpdateDto hospitalUpdateDto, Hospital hospital) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        UpdateObjectHelper.updateUserProfile(hospitalUpdateDto, hospital);
        return hospital;
    }

    public static Admin updateAdminDetailsWith(AdminUpdateDto adminUpdateDto, Admin admin) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        UpdateObjectHelper.updateUserProfile(adminUpdateDto, admin);
        return admin;
    }



}
