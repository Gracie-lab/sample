package com.medicine.medicineProject.models;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;


@EqualsAndHashCode(callSuper = true)
@Data
//@Document
public class Pharmacy extends User {

        private String pharmacyName;

        private String phoneNumber;
        private Long pharmacistLicenseNumber;
        private Long pharmacyRegistrationNumber;

        @DBRef
        private Address address;

}
