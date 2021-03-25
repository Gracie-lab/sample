package com.medicine.medicineProject.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
public class Doctor extends User {

    private String phoneNumber;

    private String licenseNumber;
    private Address address;


}
