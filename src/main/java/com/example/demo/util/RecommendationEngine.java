package com.example.demo.util;

import java.util.List;
import java.util.stream.Collectors;

public class RecommendationEngine {

    private RecommendationEngine() {}

    // Simple algorithm: return lesson IDs as CSV
    public static String generateRecommendedLessonIds(List<Long> lessonIds) {
        if (lessonIds == null || lessonIds.isEmpty()) {
            return "";
        }
        return lessonIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    // Confidence score between 0 and 1
    public static double calculateConfidence(int completed, int total) {
        if (total <= 0) return 0.0;
        double score = (double) completed / total;
        return Math.min(1.0, Math.max(0.0, score));
    }
}
