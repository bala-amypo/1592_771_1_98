package com.example.demo.service.impl;

import com.example.demo.dto.RecommendationRequest;
import com.example.demo.model.Recommendation;
import com.example.demo.model.User;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RecommendationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    private final MicroLessonRepository microLessonRepository;

    public RecommendationServiceImpl(RecommendationRepository recommendationRepository,
                                     UserRepository userRepository,
                                     MicroLessonRepository microLessonRepository) {
        this.recommendationRepository = recommendationRepository;
        this.userRepository = userRepository;
        this.microLessonRepository = microLessonRepository;
    }

    @Override
    public Recommendation generateRecommendation(Long userId, RecommendationRequest params) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Recommendation r = Recommendation.builder()
                .user(user)
                .recommendedLessonIds("1,2,3") // placeholder
                .basisSnapshot("{}")            // placeholder
                .confidenceScore(params != null && params.getConfidenceScore() != null
                        ? BigDecimal.valueOf(params.getConfidenceScore())
                        : BigDecimal.ZERO)
                .build();

        return recommendationRepository.save(r);
    }

    @Override
    public Recommendation getLatestRecommendation(Long userId) {
        List<Recommendation> list =
                recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId);

        if (list.isEmpty()) {
            throw new RuntimeException("No recommendations found");
        }
        return list.get(0);
    }

    @Override
    public List<Recommendation> getRecommendations(Long userId, LocalDate from, LocalDate to) {
        return recommendationRepository.findByUserIdAndGeneratedAtBetween(
                userId,
                from.atStartOfDay(),
                to.atTime(23, 59, 59)
        );
    }
}
