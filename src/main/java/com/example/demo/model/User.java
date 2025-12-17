package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    
    @Email
    @Column(unique = true)
    private String email;

    private String password;

    private String role;

    @Column(nullable = true)
    private String preferredLearningStyle;
    
    private LocalDateTime createdAt;

    public User(){}

    public User(String fullName, String email, String password, String role, String preferredLearningStyle,
            LocalDateTime createdAt) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.preferredLearningStyle = preferredLearningStyle;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPreferredLearningStyle() {
        return preferredLearningStyle;
    }

    public void setPreferredLearningStyle(String preferredLearningStyle) {
        this.preferredLearningStyle = preferredLearningStyle;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
}
