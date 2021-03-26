//package com.medicine.medicineProject.service;
//
//import com.medicine.medicineProject.models.User;
//import io.jsonwebtoken.Claims;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.function.Function;
//
//public interface TokenProviderService {
//    String getEmailFromToken(String token);
////
////    Date getExpiryDateFromToken(String token);
////
////    String generateLoginToken(Authentication authentication, User user);
//
//    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);
//
//    public UsernamePasswordAuthenticationToken getAuthentication(final String authenticationToken, final Authentication authentication, final UserDetails userDetails);
//
//    boolean validateToken(String token, UserDetails userDetails);
//    String generateLoginToken(Authentication authentication, User user);
//    }
