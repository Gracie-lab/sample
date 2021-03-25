package com.medicine.medicineProject.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
public class Hospital extends User {
    private String hospitalName;

    private String hospitalRegistrationNumber;
    private String phoneNumber;


    @DBRef
    private Address address;
}
