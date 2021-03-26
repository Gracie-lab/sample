//package com.medicine.medicineProject.services;
//
//import com.medicine.medicineProject.dtos.UserLoginDTO;
//import com.medicine.medicineProject.exceptions.AuthorizationException;
//import com.medicine.medicineProject.models.User;
//import com.medicine.medicineProject.repositories.UserRepository;
//import com.medicine.medicineProject.security.JWTToken;
//import com.medicine.medicineProject.service.UserPrincipalService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    UserRepository userRepository;
//
////    @Autowired
////    UserPrincipalService userPrincipalService;
//
//    @Override
//    public User saveUser(User user) {
//        return userRepository.save(user);
//    }
//
////    @Override
////    public JWTToken login(UserLoginDTO userLoginDTO) throws AuthorizationException {
////        return userPrincipalService.loginUser(userLoginDTO);
////    }
//}
