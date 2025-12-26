package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank 
    @Size(max = 100)
    private String fullName;

    @Column(unique = true, nullable = false)
    @NotBlank @Email
    private String email;

    @Column(nullable = false)
    @NotBlank @Size(min = 6)
    private String password;

    @Column(nullable = false)
    @Builder.Default
    @NotBlank
    @Pattern(regexp = "LEARNER|INSTRUCTOR|ADMIN")
    private String role = "LEARNER";

    @Column(nullable = true)
    @Size(max = 50)
    private String preferredLearningStyle;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @Builder.Default
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @Builder.Default
    private List<Progress> progresses = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @Builder.Default
    private List<Recommendation> recommendations = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) this.createdAt = LocalDateTime.now();
        if (this.role == null || this.role.isBlank()) this.role = "LEARNER";
        if (this.fullName != null) this.fullName = this.fullName.trim();
        if (this.email != null) this.email = this.email.trim().toLowerCase();
    }
}
