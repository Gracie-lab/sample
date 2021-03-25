package com.medicine.medicineProject.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
    @Id
    private String id;

    private String streetNumber;
    private String streetName;
    private String city;
    private String state;
    private String landmark;

}
