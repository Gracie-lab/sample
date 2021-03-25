package com.medicine.medicineProject.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    private String phoneNumber;

    @Email(message = "Invalid email")
    private String email;
    private String password;
}
