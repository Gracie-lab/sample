package com.medicine.medicineProject.response;

public enum Messages {
    INVALIDINPUTEXCEPTION("Invalid input");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
