package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Recommendation;
import com.example.demo.service.RecommendationService;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {
    
    @Autowired
    RecommendationService recommendationService;

    @PostMapping
    public ResponseEntity<Recommendation> generateRecommendation(@RequestParam Long userId,@RequestBody Recommendation recommendation)
    {
        return ResponseEntity.ok(recommendationService.generateRecommendation(userId, recommendation));
    }

    @GetMapping("/latest")
    public ResponseEntity<Recommendation> getLastRecommendation(@RequestParam Long userId)
    {
        return ResponseEntity.ok(recommendationService.getLastRecommendation(userId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getRecommendations(@PathVariable Long userId,@RequestParam LocalDateTime from,@RequestParam LocalDateTime to)
    {
        return ResponseEntity.ok(recommendationService.getRecommendations(userId, from, to));
    }

}
