package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "recommendations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Column(nullable = false, updatable = false)
    private LocalDateTime generatedAt;

    @NotBlank(message = "Recommended lesson IDs must not be blank")
    @Size(max = 1000, message = "Recommended lesson IDs must not exceed 1000 characters")
    @Column(nullable = false, length = 1000)
    private String recommendedLessonIds;

    @Size(max = 2000, message = "Basis snapshot must not exceed 2000 characters")
    @Column(length = 2000, nullable = true)
    private String basisSnapshot;

    @NotNull(message = "Confidence score is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Confidence score must be at least 0.0")
    @DecimalMax(value = "1.0", inclusive = true, message = "Confidence score must not exceed 1.0")
    @Column(precision = 3, scale = 2, nullable = false)
    private BigDecimal confidenceScore;

    // Ensure generatedAt is always set before persisting
    @PrePersist
    public void prePersist() {
        if (this.generatedAt == null) {
            this.generatedAt = LocalDateTime.now();
        }
    }
}








// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.*;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;

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

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "user_id", nullable = false)
//     private User user;

//     private LocalDateTime generatedAt;

//     @Column(length = 1000)
//     private String recommendedLessonIds;

//     @Column(length = 2000)
//     private String basisSnapshot;

//     private BigDecimal confidenceScore;

//     @PrePersist
//     public void prePersist() {
//         this.generatedAt = LocalDateTime.now();
//     }
// }
