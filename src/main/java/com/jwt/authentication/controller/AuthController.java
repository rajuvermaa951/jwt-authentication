package com.jwt.authentication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.authentication.dto.AuthResponse;
import com.jwt.authentication.dto.LoginRequest;
import com.jwt.authentication.dto.SignupRequest;
import com.jwt.authentication.security.JwtUtil;
import com.jwt.authentication.service.CustomUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          CustomUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest loginRequest) {

      // Authenticate Users before generating token  
    
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword()
                        )
                );

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(
                        loginRequest.getUsername()
                );

        String token = jwtUtil.generateToken(
                userDetails.getUsername(),
                userDetails.getAuthorities()
                        .iterator()
                        .next()
                        .getAuthority()
        );

        return ResponseEntity.ok(new AuthResponse(token));
    }
    
    @PostMapping("/signup")
    public String addUser(@RequestBody SignupRequest signupRequest)
    {
		return userDetailsService.addUser(signupRequest);
    	
    }
}