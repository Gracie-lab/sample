//package com.medicine.medicineProject.service;
//
//import com.medicine.medicineProject.dtos.UserLoginDTO;
//import com.medicine.medicineProject.exceptions.AuthorizationException;
//import com.medicine.medicineProject.models.User;
//import com.medicine.medicineProject.repositories.PatientRepository;
//import com.medicine.medicineProject.repositories.UserRepository;
//import com.medicine.medicineProject.security.AppAuthenticationProvider;
//import com.medicine.medicineProject.security.ApplicationUser;
//import com.medicine.medicineProject.security.JWTToken;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.Optional;
//
//@Slf4j
//@Service
//public class UserPrincipalService implements UserDetailsService, UserDetails {
//
//    @Autowired
//    PatientRepository patientRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//
//    @Autowired
//    TokenProviderServiceImpl tokenProviderService;
//
//    @Autowired
//    private AppAuthenticationProvider authenticationManager;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> optionalUser = userRepository.findByEmail(getUsername());
//        if(optionalUser.isEmpty()){
//            throw new UsernameNotFoundException("User with given email not found");
//        }
//        else{
//            User user =  optionalUser.get();
//            return ApplicationUser.create(user);
//        }
//    }
//
//    public UserDetails loadUserByEmail(String email)throws UsernameNotFoundException{
//        return loadUserByUsername(email);
//    }
//
//    private String getUserNameFromDto(UserLoginDTO userLoginDTO){
//        if (userLoginDTO.getPhoneNumber() != null) {
//            return userLoginDTO.getPhoneNumber();
//        }
//        else return userLoginDTO.getEmail();
//    }
//
//    public JWTToken loginUser(UserLoginDTO userLoginDTO) throws AuthorizationException {
//        Optional<User> user = userRepository.findByEmail(userLoginDTO.getEmail());
//        log.info(user.get().getPassword() + " user password ==================================");
//        log.info(passwordEncoder.encode(userLoginDTO.getPassword()) + " encoded dto password ++++++++++++++++++++++++++++++++++");
//        log.info(String.valueOf(passwordEncoder.matches(userLoginDTO.getPassword(), user.get().getPassword())));
//        final Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        getUserNameFromDto(userLoginDTO),
//                       userLoginDTO.getPassword()
//                        )
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        user = userRepository.findByEmail(userLoginDTO.getEmail());
////        Optional<User> user = findByUsername(userLoginDTO);
//
//        JWTToken jwtToken = new JWTToken(tokenProviderService.generateLoginToken(authentication, user.get()));
//
//        log.info("JWT object -> {}", jwtToken.toString());
//        return jwtToken;
//
//
//
//
//    }
//
////    private Optional<User> findByUsername(UserLoginDTO userLoginDTO){
////        if(userLoginDTO.getPhoneNumber() != null){
////            return userRepository.findByPhoneNumber(userLoginDTO.getPhoneNumber());
////        }
////        else{
////            return userRepository.findByEmail(userLoginDTO.getEmail());
////        }
////    }
//
//}
