// package com.example.demo.entity;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.PrePersist;
// import jakarta.persistence.Table;
// import jakarta.validation.constraints.DecimalMax;
// import jakarta.validation.constraints.DecimalMin;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Size;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Table(name = "recommendations")
// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor

// public class Recommendation {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne(fetch = FetchType.LAZY, optional = false)
//     @JoinColumn(name = "user_id", nullable = false)
//     @JsonIgnore
//     private User user;

//     @Column(nullable = false, updatable = false)
//     private LocalDateTime generatedAt;

//     @NotBlank(message = "Recommended lesson IDs must not be blank")
//     @Size(
//         max = 1000,
//         message = "Recommended lesson IDs must not exceed 1000 characters"
//     )
//     @Column(nullable = false, length = 1000)
//     private String recommendedLessonIds;

//     @Size(
//         max = 2000,
//         message = "Basis snapshot must not exceed 2000 characters"
//     )
//     @Column(length = 2000, nullable = true)
//     private String basisSnapshot;

//     @NotNull(message = "Confidence score is required")
//     @DecimalMin(
//         value = "0.0",
//         inclusive = true,
//         message = "Confidence score must be at least 0.0"
//     )
//     @DecimalMax(
//         value = "1.0",
//         inclusive = true,
//         message = "Confidence score must not exceed 1.0"
//     )
//     @Column(precision = 3, scale = 2, nullable = false)
//     private BigDecimal confidenceScore;

//     @PrePersist
//     public void onCreate() {
//         this.generatedAt = LocalDateTime.now();
//     }
// }








package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {

    @Id
    @GeneratedValue
    private Long id;

    private String recommendedLessonIds;
    private String basisSnapshot;
    private BigDecimal confidenceScore;

    private LocalDateTime generatedAt;

    @PrePersist
    public void prePersist() {
        this.generatedAt = LocalDateTime.now();
    }
}
