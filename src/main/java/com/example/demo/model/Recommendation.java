package com.example.demo.model;

public class Recommendation {
    private long id;
    private User user;
    private LocalDateTime generatedAt;
    private String recommendedLessonIds;
    private String basisSnapshot;
    private Double confidenceScore;

    public Recommendation(){}
}
