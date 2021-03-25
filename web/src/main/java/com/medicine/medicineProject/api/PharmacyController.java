package com.medicine.medicineProject.api;


import com.medicine.medicineProject.dtos.PharmacyUpdateDto;
import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;
//import com.medicine.medicineProject.services.PharmacyService;
import com.medicine.medicineProject.exceptions.UpdateUserException;
import com.medicine.medicineProject.exceptions.UserDoesNotExistException;
import com.medicine.medicineProject.models.Pharmacy;
import com.medicine.medicineProject.response.ApiResponse;
import com.medicine.medicineProject.services.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {

    @Autowired
    PharmacyService pharmacyService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDto registerDto){

        try {
            pharmacyService.register(registerDto);

        }
        catch (RegisterException | InvalidInputException exe){
            return ResponseEntity.badRequest().body(exe.getMessage());
        }
        return new ResponseEntity<>(new ApiResponse(true, "Registered successfully"), HttpStatus.CREATED);
    }

    @PatchMapping("/updateProfile/{pharmacyId}")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody PharmacyUpdateDto pharmacyUpdateDto, @PathVariable("pharmacyId") String pharmacyId) throws InvocationTargetException, NoSuchMethodException, UpdateUserException, IllegalAccessException {

        Pharmacy updatedPharmacy;
        try {
            updatedPharmacy = pharmacyService.updateProfile(pharmacyUpdateDto, pharmacyId);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException
                | UpdateUserException | UserDoesNotExistException exe) {
            return ResponseEntity.badRequest().body(exe.getMessage());
        }

        return ResponseEntity.ok(updatedPharmacy);
    }
}