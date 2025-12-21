
package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Recommendation;
import com.example.demo.service.RecommendationService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/recommendations")
@Tag(name = "Recommendations")

public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/generate")
    public ResponseEntity<Recommendation> generateRecommendation(@RequestParam Long userId,
            @RequestBody Recommendation recommendation) {
        return ResponseEntity.ok(recommendationService.generateRecommendation(userId, recommendation));
    }

    @GetMapping("/latest")
    public ResponseEntity<Recommendation> getLastRecommendation(@RequestParam Long userId) {
        return ResponseEntity.ok(recommendationService.getLastRecommendation(userId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getRecommendations(@PathVariable Long userId,
            @RequestParam LocalDateTime from, @RequestParam LocalDateTime to) {
                List<Recommendation> recommendations=recommendationService.getRecommendations(userId, from, to);
                if(recommendations.isEmpty())
                {
                    return ResponseEntity.status(404).body("No recommendations found for user "+userId+" in the given data range");
                }
        return ResponseEntity.ok(recommendations);
    }

}

