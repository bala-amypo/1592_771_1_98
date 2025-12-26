// package com.example.demo.entity;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.PrePersist;
// import jakarta.persistence.Table;
// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.Pattern;
// import jakarta.validation.constraints.Size;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Table(name = "users")
// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor

// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @NotBlank(message = "Full name is required")
//     @Size(max = 100, message = "Full name must not exceed 100 characters")
//     private String fullName;

//     @Column(unique = true, nullable = false)
//     @NotBlank(message = "Email is required")
//     @Email(message = "Invalid email format")
//     private String email;

//     @Column(nullable = false)
//     @NotBlank(message = "Password is required")
//     @Size(min = 8, message = "Password must be at least 8 characters")
//     private String password;

//     @Column(nullable = false)
//     @Builder.Default
//     @NotBlank(message = "Role is required")
//     @Pattern(regexp = "LEARNER|INSTRUCTOR|ADMIN", message = "Role must be LEARNER, INSTRUCTOR, or ADMIN")
//     private String role = "LEARNER";

//     @Column(nullable = true)
//     @Size(max = 50, message = "Preferred learning style max 50 characters")
//     private String preferredLearningStyle;

//     private LocalDateTime createdAt;

//     @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//     @JsonIgnore
//     @Builder.Default
//     private List<Course> courses = new ArrayList<>();

//     @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//     @JsonIgnore
//     @Builder.Default
//     private List<Progress> progresses = new ArrayList<>();

//     @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//     @JsonIgnore
//     @Builder.Default
//     private List<Recommendation> recommendations = new ArrayList<>();

//     @PrePersist
//     protected void onCreate() {
//         this.createdAt = LocalDateTime.now();
//         if (this.role == null) {
//             this.role = "LEARNER";
//         }
//     }
// }




package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    private String fullName;

    @Column(unique = true)
    private String email;

    private String password;

    @Builder.Default
    private String role = "LEARNER";

    private String preferredLearningStyle;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.role == null) {
            this.role = "LEARNER";
        }
    }
}
