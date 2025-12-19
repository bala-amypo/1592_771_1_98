package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.model.Recommendation;

public interface RecommendationService {
    
    Recommendation generateRecommendation(Long userId,Recommendation recommendation);
    Recommendation getLastRecommendation(Long userId);
    List<Recommendation> getRecommendations(Long userId,LocalDateTime from,LocalDateTime to);
}
