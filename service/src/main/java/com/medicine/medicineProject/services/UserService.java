package com.medicine.medicineProject.services;

import com.medicine.medicineProject.dtos.UserLoginDTO;
import com.medicine.medicineProject.exceptions.AuthorizationException;
import com.medicine.medicineProject.models.User;
//import com.medicine.medicineProject.security.JWTToken;

public interface UserService {
    User saveUser(User user);
//    public JWTToken login(UserLoginDTO userLoginDTO) throws AuthorizationException;
}
