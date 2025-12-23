// package com.example.demo.service.impl;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;
// import java.util.List;

// import org.springframework.stereotype.Service;

// import com.example.demo.entity.Recommendation;
// import com.example.demo.entity.User;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.MicroLessonRepository;
// import com.example.demo.repository.ProgressRepository;
// import com.example.demo.repository.RecommendationRepository;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.RecommendationService;

// import jakarta.transaction.Transactional;

// @Service
// @Transactional
// public class RecommendationServiceImpl implements RecommendationService {

//     private final RecommendationRepository recommendationRepository;
//     private final ProgressRepository progressRepository;
//     private final MicroLessonRepository microLessonRepository;
//     private final UserRepository userRepository;

//     public RecommendationServiceImpl(RecommendationRepository recommendationRepository,
//             ProgressRepository progressRepository, MicroLessonRepository microLessonRepository,
//             UserRepository userRepository) {
//         this.recommendationRepository = recommendationRepository;
//         this.progressRepository = progressRepository;
//         this.microLessonRepository = microLessonRepository;
//         this.userRepository = userRepository;
//     }

//     @Override
//     public Recommendation generateRecommendation(Long userId, Recommendation recommendation) {
//         User user = userRepository.findById(userId)
//                 .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + userId));

//         if (recommendation.getConfidenceScore().compareTo(BigDecimal.ZERO) < 0
//                 || recommendation.getConfidenceScore().compareTo(BigDecimal.ONE) > 0) {
//             throw new IllegalArgumentException("Confidence score must be between 0.0 and 1.0");
//         }
//         Recommendation toSave = Recommendation.builder()
//                 .user(user)
//                 .recommendedLessonIds(recommendation.getRecommendedLessonIds())
//                 .basisSnapshot(recommendation.getBasisSnapshot())
//                 .confidenceScore(recommendation.getConfidenceScore())
//                 .build();

//         return recommendationRepository.save(toSave);
//     }

//     @Override
//     public Recommendation getLastRecommendation(Long userId) {
//         return recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId).stream().findFirst()
//                 .orElseThrow(() -> new ResourceNotFoundException("No recommendation found for user " + userId));
//     }

//     @Override
//     public List<Recommendation> getRecommendations(Long userId, LocalDateTime from, LocalDateTime to) {
//         return recommendationRepository.findByUserIdAndGeneratedAtBetween(userId, from, to);
//     }
// }




package com.example.demo.service.impl;

import com.example.demo.model.Recommendation;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.MicroLessonRepository;

import java.util.List;

public class RecommendationServiceImpl {

    private final RecommendationRepository repo;

    public RecommendationServiceImpl(
            RecommendationRepository r,
            UserRepository u,
            MicroLessonRepository m) {
        this.repo = r;
    }

    public Recommendation getLatestRecommendation(Long userId) {
        List<Recommendation> list = repo.findByUserIdOrderByGeneratedAtDesc(userId);
        if (list.isEmpty()) throw new RuntimeException();
        return list.get(0);
    }
}
