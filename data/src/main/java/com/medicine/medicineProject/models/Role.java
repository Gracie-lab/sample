package com.medicine.medicineProject.models;

public enum Role {
    CUSTOMER, PHARMACY, ADMIN, DOCTOR, HOSPITAL;



    @Override
    public String toString() {
        switch (this){
            case ADMIN:return "Admin";
            case CUSTOMER: return "Customer";
            case DOCTOR: return "Doctor";
            case HOSPITAL:return "Hospital";
            case PHARMACY:return "Pharmacy";
            default:return null;
        }
    }
}
