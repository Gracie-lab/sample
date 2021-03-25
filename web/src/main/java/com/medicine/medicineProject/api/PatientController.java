package com.medicine.medicineProject.api;

import com.medicine.medicineProject.dtos.PatientUpdateDto;
import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;
import com.medicine.medicineProject.exceptions.UpdateUserException;
import com.medicine.medicineProject.models.Patient;
import com.medicine.medicineProject.response.ApiResponse;
import com.medicine.medicineProject.services.PatientService;
import com.medicine.medicineProject.service.TokenProviderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;

@Slf4j
@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    TokenProviderServiceImpl tokenProviderService;


    @CrossOrigin("localhost:3000")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDto registerDto){

        try {
            patientService.register(registerDto);
        }
        catch (RegisterException | InvalidInputException exe){
            return ResponseEntity.badRequest().body(exe.getMessage());
        }
        return new ResponseEntity<>(new ApiResponse(true, "Registered successfully"), HttpStatus.CREATED);
    }

    @PatchMapping("/updateProfile")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody PatientUpdateDto patientUpdateDto, @RequestHeader("Authorization") String userToken) throws InvocationTargetException, NoSuchMethodException, UpdateUserException, IllegalAccessException {
        log.info("got here");

        Patient updatedPatient;
        try {
            String userUniqueToken = tokenProviderService.getEmailFromToken(userToken);
            System.out.println(userUniqueToken);
            updatedPatient = patientService.updateProfile(patientUpdateDto, userUniqueToken);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException
                | UpdateUserException exe) {
            return ResponseEntity.badRequest().body(exe.getMessage());
        }

        return ResponseEntity.ok(updatedPatient);
    }


}

