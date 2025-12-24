package com.example.demo.dto;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class RecommendationRequest {
    private String tags;
    private String difficulty;
    private String contentType;
    private Integer limit;
    private String preferredLearningStyle;
    private Double confidenceScore;
}
