//package com.medicine.medicineProject.security;
//
//import com.medicine.medicineProject.models.Role;
//import com.medicine.medicineProject.models.User;
//import com.medicine.medicineProject.repositories.UserRepository;
//import com.medicine.medicineProject.service.UserPrincipalService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Primary;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Service
//@Primary
//public class AppAuthenticationProvider implements AuthenticationManager {
//    @Autowired
//    private UserPrincipalService userPrincipalService;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
//        String username = token.getName();
//        String password = (String) token.getCredentials();
//         log.info(password);
////        Optional<User> user = userRepository.findByEmail(username);
//        Optional<User> user = userRepository.findByUsername(username);
//        if (!user.isPresent()) {
//            throw new BadCredentialsException("There is not account with given credentials");
//        }
//        if (!passwordEncoder.matches(password, user.get().getPassword())) {
//            throw new BadCredentialsException("Invalid username or password");
//        }
//        List<Role> authorities = user.get().getRoles();
//        return new UsernamePasswordAuthenticationToken(username, password, authorities.stream().map(authority
//                -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList()));
//    }
//}
