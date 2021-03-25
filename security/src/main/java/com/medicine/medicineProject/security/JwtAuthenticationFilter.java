package com.medicine.medicineProject.security;

import com.medicine.medicineProject.service.TokenProviderServiceImpl;
import com.medicine.medicineProject.service.UserPrincipalService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserPrincipalService userPrincipalService;

    @Autowired
    TokenProviderServiceImpl tokenProvider;

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean(){
        return new JwtAuthenticationFilter();
    }

//    @Autowired
//    private TokenProvider tokenProvider;
    private String HEADER_STRING = "Authorization";

    private String TOKEN_PREFIX = "Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(HEADER_STRING);
        String username = null;
        String authenticationToken = null;
        if (header != null && header.startsWith(TOKEN_PREFIX + " ")) {
            int TOKEN_PREFIX_LENGTH = 7;
            authenticationToken = header.substring(TOKEN_PREFIX_LENGTH);
            log.info(authenticationToken + " authentication token =================================");
            try {
                username = tokenProvider.getEmailFromToken(authenticationToken);
            } catch (IllegalArgumentException e) {
                logger.error("error retrieving username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("invalid token");
            } catch (SignatureException e) {
                logger.error("Invalid username or password");
            }
        } else{
                logger.warn("bearer string not found. Ignoring header");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userPrincipalService.loadUserByEmail(username);

            if(tokenProvider.validateToken(authenticationToken, userDetails)){
                UsernamePasswordAuthenticationToken authentication = tokenProvider.getAuthentication(authenticationToken,
                                SecurityContextHolder.getContext()
                                .getAuthentication(), userDetails);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
