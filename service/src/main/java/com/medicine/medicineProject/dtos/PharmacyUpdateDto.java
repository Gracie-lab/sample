package com.medicine.medicineProject.dtos;

import com.medicine.medicineProject.models.Address;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class PharmacyUpdateDto {
    private String firstNameOfContactPerson;
    private String lastNameOfContactPerson;
    private String pharmacyName;


    @Digits(integer = 11, fraction = 0)
    @Pattern(regexp = "^[0]\\d{10}$")
    @Size(min=11, max=11, message = "Invalid phone number")
    private String phoneNumber;

    private Address address;
}
