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
