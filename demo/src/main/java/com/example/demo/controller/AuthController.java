package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.web.dto.UserRegistrationDto;

import com.example.demo.web.dto.loginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUserAccount(@RequestBody UserRegistrationDto registrationDto) {
        try {
            User savedUser = userService.save(registrationDto);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Échec de l'inscription : " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody loginDto loginDto) {
        try {
            Optional<User> userOpt = userService.login(loginDto.getEmail(), loginDto.getPassword());

            if (userOpt.isPresent()) {
                User user = userOpt.get();
                return ResponseEntity.ok(new LoginResponse(user.getEmail(), user.getRoles(), user.isPharmacy()));
            } else {
                return ResponseEntity.status(401).body("Identifiants invalides");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de la connexion : " + e.getMessage());
        }
    }

    // DTO pour la réponse du login
    private record LoginResponse(String email, Object roles, boolean isPharmacy) {}
}
