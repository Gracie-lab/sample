package com.medicine.medicineProject.models;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;


import java.util.Date;


@EqualsAndHashCode(callSuper = true)
//@Document
@Data
public class Patient extends User {


    @DBRef
    private Address address;

    private String phoneNumber;

    private Date dateOfBirth;

    private Gender gender;



}
