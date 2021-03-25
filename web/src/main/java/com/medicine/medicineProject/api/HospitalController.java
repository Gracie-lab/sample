package com.medicine.medicineProject.api;

import com.medicine.medicineProject.dtos.HospitalUpdateDto;
import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;
import com.medicine.medicineProject.exceptions.UpdateUserException;
import com.medicine.medicineProject.exceptions.UserDoesNotExistException;
import com.medicine.medicineProject.models.Hospital;
import com.medicine.medicineProject.response.ApiResponse;
import com.medicine.medicineProject.services.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/hospital")
public class HospitalController {
    @Autowired
    HospitalService hospitalService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDto registerDto){
        try{
            hospitalService.register(registerDto);
        } catch (InvalidInputException | RegisterException exe) {
            return ResponseEntity.badRequest().body(exe.getMessage());
        }
        return new ResponseEntity<>(new ApiResponse(true, "Registered successfully"), HttpStatus.CREATED);

    }

    @PatchMapping("/updateProfile/{HospitalId}")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody HospitalUpdateDto hospitalUpdateDto, @PathVariable("HospitalId") String hospitalId
    ) throws InvocationTargetException, NoSuchMethodException, UpdateUserException, IllegalAccessException {

        Hospital updatedHospital;
        try {
            updatedHospital = hospitalService.updateProfile(hospitalUpdateDto, hospitalId);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException
                | UpdateUserException | UserDoesNotExistException exe) {
            return ResponseEntity.badRequest().body(exe.getMessage());
        }

        return ResponseEntity.ok(updatedHospital);
    }
}
