package com.example.healthHub.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;

//@Entity
public class Patient {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullname;
    private String bloodGroup;
    private Integer age;
    private String address;
    private Instant createdAt;
    private Instant updatedAt;
}
