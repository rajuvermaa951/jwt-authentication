package com.jwt.authentication.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, authenticated user!";
    }

    @GetMapping("/user")
    public String currentUser(Authentication authentication) {
        return "Logged in user: " + authentication.getName();
    }
}