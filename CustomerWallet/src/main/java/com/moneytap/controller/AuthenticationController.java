package com.moneytap.controller;

import com.moneytap.model.CustomerResponseToken;
import com.moneytap.model.CustomerTokenRequest;
import com.moneytap.repository.JwtRepository;
import com.moneytap.service.UserService;
import com.moneytap.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Autowired
    private JwtRepository jwtRepository;


    @PostMapping("/authenticate")
    public CustomerResponseToken authenticate(@RequestBody CustomerTokenRequest jwtRequest) throws Exception{
        jwtRepository.deleteAll();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);
        jwtRepository.save(new CustomerResponseToken(token));
        return  new CustomerResponseToken(token);
    }
}
