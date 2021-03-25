package com.medicine.medicineProject.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/home")
    public String home(){
        return ("Server is up and running.");
    }
}

