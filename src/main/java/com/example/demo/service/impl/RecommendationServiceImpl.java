package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Recommendation;
import com.example.demo.model.User;
import com.example.demo.repository.RecommendationRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RecommendationService;

@Service
public class RecommendationServiceImpl implements RecommendationService{

    @Autowired
    RecommendationRepository recommendationRepository;

    @Autowired
    UserRepository userRepository;
    
    public Recommendation generateRecommendation(Long userId,Recommendation recommendation)
    {
        User user=userRepository.findById(userId)
        .orElseThrow(()-> new ResourceNotFoundException("User not found with id:"+userId));

        recommendation.setUser(user);
        recommendation.setGeneratedAt(LocalDateTime.now());

        return recommendationRepository.save(recommendation);
    }

    public Recommendation getLastRecommendation(Long userId)
    {
        return recommendationRepository.findByUserIdOrderByGeneratedAtDesc(userId).stream().findFirst()
        .orElseThrow(()-> new ResourceNotFoundException("No recommendation found for user "+userId));
    }

    public List<Recommendation> getRecommendations(Long userId,LocalDateTime from,LocalDateTime to)
    {
        return recommendationRepository.findByUserIdAndGeneratedAtBetween(userId, from, to);
    }
}
