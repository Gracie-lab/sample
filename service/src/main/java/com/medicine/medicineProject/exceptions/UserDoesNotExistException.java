package com.medicine.medicineProject.exceptions;

public class UserDoesNotExistException extends ApplicationException{
    public UserDoesNotExistException(String message) {
        super(message);
    }
}
