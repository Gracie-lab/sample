package com.medicine.medicineProject.repositories;

import com.medicine.medicineProject.models.Pharmacy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PharmacyRepository extends MongoRepository<Pharmacy, String>{
    Optional<Pharmacy> findByEmail(String email);


}
