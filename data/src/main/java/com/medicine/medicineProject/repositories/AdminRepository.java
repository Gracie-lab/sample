package com.medicine.medicineProject.repositories;

import com.medicine.medicineProject.models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
    Optional<Admin>findByEmail(String email);
    Optional<Admin> findByPhoneNumber(String phoneNumber);
}
