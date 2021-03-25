package com.medicine.medicineProject.dtos;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RegisterDto {

    @Email(message = "Invalid email")
    private String email;

    @Digits(integer = 11, fraction = 0)
    @Pattern(regexp = "^[0]\\d{10}$")
    @Size(min=11, max=11, message = "Invalid phone number")
    private String phoneNumber;

    private String password;
}
