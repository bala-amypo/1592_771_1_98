package com.example.demo.controller;

import com.example.demo.dto.RecommendationRequest;
import com.example.demo.model.Recommendation;
import com.example.demo.service.RecommendationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/recommendations")
@Tag(name = "Recommendations")
@SecurityRequirement(name = "bearerAuth")
public class RecommendationController {

    private final RecommendationService recommendationService;

        public RecommendationController(RecommendationService recommendationService) {
                this.recommendationService = recommendationService;
                    }

                        @PostMapping("/generate")
                            public ResponseEntity<Recommendation> generate(
                                        @RequestParam Long userId,
                                                    @RequestBody RecommendationRequest request) {

                                                            return ResponseEntity.ok(
                                                                            recommendationService.generateRecommendation(userId, request)
                                                                                    );
                                                                                        }

                                                                                            @GetMapping("/latest")
                                                                                                public ResponseEntity<Recommendation> latest(
                                                                                                            @RequestParam Long userId) {

                                                                                                                    return ResponseEntity.ok(
                                                                                                                                    recommendationService.getLatestRecommendation(userId)
                                                                                                                                            );
                                                                                                                                                }

                                                                                                                                                    @GetMapping("/user/{userId}")
                                                                                                                                                        public ResponseEntity<List<Recommendation>> getUserRecommendations(
                                                                                                                                                                    @PathVariable Long userId,
                                                                                                                                                                                @RequestParam LocalDate from,
                                                                                                                                                                                            @RequestParam LocalDate to) {

                                                                                                                                                                                                    return ResponseEntity.ok(
                                                                                                                                                                                                                    recommendationService.getRecommendations(userId, from, to)
                                                                                                                                                                                                                            );
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                