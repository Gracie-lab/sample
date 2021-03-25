package com.medicine.medicineProject.api;

import com.medicine.medicineProject.dtos.DoctorUpdateDto;
import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;
import com.medicine.medicineProject.exceptions.UpdateUserException;
import com.medicine.medicineProject.exceptions.UserDoesNotExistException;
import com.medicine.medicineProject.models.Doctor;
import com.medicine.medicineProject.response.ApiResponse;
import com.medicine.medicineProject.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;


@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/register")
    public ResponseEntity<?> registerDoctor(@Valid @RequestBody RegisterDto registerDto){
        try {
            doctorService.register(registerDto);
        }
        catch (RegisterException | InvalidInputException exe){
            return ResponseEntity.badRequest().body(exe.getMessage());
        }
        return new ResponseEntity<>(new ApiResponse(true, "Registered successfully"), HttpStatus.CREATED);
    }

    @PatchMapping("/updateProfile/{doctorId}")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody DoctorUpdateDto doctorUpdateDto, @PathVariable("doctorId") String doctorId) throws InvocationTargetException, NoSuchMethodException, UpdateUserException, IllegalAccessException {

        Doctor updatedDoctor;
        try {
            updatedDoctor = doctorService.updateProfile(doctorUpdateDto, doctorId);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException
                | UpdateUserException | UserDoesNotExistException exe) {
            return ResponseEntity.badRequest().body(exe.getMessage());
        }

        return ResponseEntity.ok(updatedDoctor);
    }
}
