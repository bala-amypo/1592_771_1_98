package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotNull
    private User user;

    @Column(nullable = false, updatable = false)
    private LocalDateTime generatedAt;

    @NotBlank
    @Size(max = 1000)
    @Column(nullable = false, length = 1000)
    private String recommendedLessonIds;

    @Size(max = 2000)
    @Column(length = 2000,nullable = true)
    private String basisSnapshot;

    @NotNull
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "1.0")
    @Column(precision = 3, scale = 2, nullable = false)
    private BigDecimal confidenceScore;

    @PrePersist
    public void onCreate() {
        this.generatedAt = LocalDateTime.now();
    }
}