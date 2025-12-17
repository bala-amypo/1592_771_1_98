package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Recommendation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private User user;

    private LocalDateTime generatedAt;

    private String recommendedLessonIds;

    private String basisSnapshot;

    @Min(value = 0)
    @Max(value = 1)
    private Double confidenceScore;
    
    public Recommendation(){}

    public Recommendation(User user, LocalDateTime generatedAt, String recommendedLessonIds, String basisSnapshot,
            Double confidenceScore) {
        this.user = user;
        this.generatedAt = generatedAt;
        this.recommendedLessonIds = recommendedLessonIds;
        this.basisSnapshot = basisSnapshot;
        this.confidenceScore = confidenceScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public String getRecommendedLessonIds() {
        return recommendedLessonIds;
    }

    public void setRecommendedLessonIds(String recommendedLessonIds) {
        this.recommendedLessonIds = recommendedLessonIds;
    }

    public String getBasisSnapshot() {
        return basisSnapshot;
    }

    public void setBasisSnapshot(String basisSnapshot) {
        this.basisSnapshot = basisSnapshot;
    }

    public Double getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(Double confidenceScore) {
        this.confidenceScore = confidenceScore;
    }

    

}
