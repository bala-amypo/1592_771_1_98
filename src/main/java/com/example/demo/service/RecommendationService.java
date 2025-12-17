package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.RecommendationRequest;
import com.example.demo.model.Recommendation;

public interface RecommendationService {
    Recommendation generateRecommendation(Long userId,RecommendationRequest params);
    Recommendation getLatestRecommendation(Long userId);
    List<Recommendation> getRecommendations(Long userId,LocalDate from,LocalDate to);
}
