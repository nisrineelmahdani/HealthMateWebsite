package com.example.demo.controller;


import com.example.demo.service.UserService;
import com.example.demo.web.dto.UserRegistrationDto;
import com.example.demo.web.dto.loginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3000") // Allow localhost:3000 (React front-end) to make requests

@RestController
@RequestMapping("/api/auth")
public class AuthController<LoginDto> {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUserAccount(@RequestBody UserRegistrationDto registrationDto) {
        try {
            userService.save(registrationDto);  // Call the servi
            // ce to save the user
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Registration failed: " + e.getMessage());
        }
    }

    // Login can be handled using Spring Security for JWT authentication or other methods
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody loginDto loginDto) {
        try {
            boolean isAuthenticated = userService.authenticate(loginDto.getEmail(), loginDto.getPassword());
            if (isAuthenticated) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error during login: " + e.getMessage());
        }
    }

}

