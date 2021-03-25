package com.medicine.medicineProject.api;

import com.medicine.medicineProject.dtos.AdminUpdateDto;
import com.medicine.medicineProject.dtos.RegisterDto;
import com.medicine.medicineProject.exceptions.InvalidInputException;
import com.medicine.medicineProject.exceptions.RegisterException;
import com.medicine.medicineProject.exceptions.UserDoesNotExistException;
import com.medicine.medicineProject.models.Admin;
import com.medicine.medicineProject.response.ApiResponse;
import com.medicine.medicineProject.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody RegisterDto registerDto){
        try{
            adminService.register(registerDto);

        }
        catch (RegisterException | InvalidInputException exe) {
            return ResponseEntity.badRequest().body(exe.getMessage());
        }
        return new ResponseEntity<>(new ApiResponse(true,"Registered successfully"), HttpStatus.CREATED);

    }

    @PatchMapping("/updateProfile/{adminId}")
    public ResponseEntity<?> updateAdminDetails(@Valid @RequestBody AdminUpdateDto adminUpdateDto, @PathVariable String id) {
        Admin updatedAdmin;
        try {
            updatedAdmin = adminService.updateProfile(adminUpdateDto, id);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | UserDoesNotExistException exe) {
            return ResponseEntity.badRequest().body(exe.getMessage());
        }
        return ResponseEntity.ok(updatedAdmin);
    }
}
