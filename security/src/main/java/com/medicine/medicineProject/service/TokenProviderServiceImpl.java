//package com.medicine.medicineProject.service;
//
//import com.medicine.medicineProject.models.User;
//import io.jsonwebtoken.*;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.io.Serializable;
//import java.util.Arrays;
//import java.util.Base64;
//import java.util.Collection;
//import java.util.Date;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//import static com.medicine.medicineProject.security.SecurityConstants.*;
//
//@Slf4j
//@Data
//@Service
//@Configuration
//@PropertySource("classpath:securityConstants.properties")
//public class TokenProviderServiceImpl implements Serializable, TokenProviderService {
//
////    private int ACCESS_TOKEN_VALIDITY;
////    private String AUTHORITIES_KEY ;
//    Logger logger = LoggerFactory.getLogger(TokenProviderServiceImpl.class);
////
////   private String BEARER_TOKEN_VALIDITY;
////
////    private String SIGNING_KEY_STRING = "secret";
//
//
//
//    private String getEncryptedSigningKey(){
//
//        String encryptedSigningKey =  Base64.getEncoder().encodeToString(SIGNING_KEY_STRING.getBytes());
//        log.info("encrypted signing key ");
//        return encryptedSigningKey;
//    }
//
//    @Override
//    public String getEmailFromToken(String token) {
//        return getClaimFromToken(token, Claims::getSubject);
//
//
//    }
//
//    @Override
//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = getAllClaimsFromToken(token);
//        log.info(claims.getSubject() + "+++++++++++++++ claim subject");
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims getAllClaimsFromToken(String token) {
//        Claims claims;
//        try{
//            claims = Jwts.parser()
//                    .setSigningKey(getEncryptedSigningKey())
//                    .parseClaimsJws(token)
//                    .getBody();
//            return claims;
//        }
//        catch (SignatureException ex){
//            logger.error("untrusted token detected and invalidated");
//            throw new SecurityException("token untrusted");
//
//        }
//
//    }
//
//    @Override
//    public boolean validateToken(String token, UserDetails userDetails){
//        final String email = getEmailFromToken(token);
//        boolean tokenStatus = email.equals(userDetails.getUsername()) && (!isTokenExpired(token));
//        return tokenStatus;
//
//    }
//
//    @Override
//    public String generateLoginToken(Authentication authentication, User user) {
//        final String authorities = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//        log.info(authorities);
//
//        String jwts = Jwts.builder()
//                .setSubject(authentication.getName())
//                .claim(AUTHORITIES_KEY, authorities)
////                .claim("id", user.getId())
//                .setIssuer("MEDICINECOM")
//                .signWith(SignatureAlgorithm.HS512, getEncryptedSigningKey())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()
//                + EXPIRATION_DATE))
//                .compact();
//            log.info("JWT token witj claims -> {}", jwts);
//            return jwts;
//
//    }
//
//    private boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//
//    private Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
//
//    @Override
//    public UsernamePasswordAuthenticationToken getAuthentication(final String authenticationToken, final Authentication authentication, final UserDetails userDetails) {
//        final JwtParser jwtParser = Jwts.parser().setSigningKey(getEncryptedSigningKey());
//
//        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(authenticationToken);
//
//        final Claims claims = claimsJws.getBody();
//
//        final Collection<? extends GrantedAuthority> authorities =
//                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
//                    .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//
//        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
//    }
//}
