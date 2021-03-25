package com.medicine.medicineProject.repositories;

import com.medicine.medicineProject.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
//    Optional<User> findByPhoneNumber(String phoneNumber);

}
