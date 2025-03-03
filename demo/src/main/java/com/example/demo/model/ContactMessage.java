package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private String name;

    private  String phone;

    @Column(nullable = false , columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false , updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
