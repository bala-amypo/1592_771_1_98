// package com.example.demo.service;

// import java.time.LocalDateTime;
// import java.util.List;

// import com.example.demo.entity.Recommendation;

// public interface RecommendationService {

//     Recommendation generateRecommendation(Long userId, Recommendation recommendation);

//     Recommendation getLastRecommendation(Long userId);

//     List<Recommendation> getRecommendations(Long userId, LocalDateTime from, LocalDateTime to);
// }





package com.example.demo.service;

import com.example.demo.dto.RecommendationRequest;
import com.example.demo.model.Recommendation;

import java.time.LocalDate;
import java.util.List;

public interface RecommendationService {

    Recommendation generateRecommendation(Long userId, RecommendationRequest params);

    Recommendation getLatestRecommendation(Long userId);

    List<Recommendation> getRecommendations(Long userId, LocalDate from, LocalDate to);
}
