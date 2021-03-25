package com.medicine.medicineProject.exceptions;

import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;

public class InvalidInputException extends ApplicationException {
    public InvalidInputException(String message){
        super(message);
    }

     HashMap<Class, String> message = new HashMap<>();

    public void setMessage(HashMap<Class, String> message) {
        message.put(MethodArgumentNotValidException.class, "Invalid email");
    }

//    public HashMap<Class, String> getMessage() {
//        return message;
//    }
}