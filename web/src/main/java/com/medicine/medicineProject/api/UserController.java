package com.medicine.medicineProject.api;

import com.medicine.medicineProject.dtos.UserLoginDTO;
import com.medicine.medicineProject.exceptions.AuthorizationException;
import com.medicine.medicineProject.response.ApiResponse;
import com.medicine.medicineProject.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginDTO userLoginDTO){
        log.info("got here");
        try{
            userService.login(userLoginDTO);
        }
        catch (AuthorizationException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(new ApiResponse(true, "You are now logged in."), HttpStatus.OK);
    }
}
