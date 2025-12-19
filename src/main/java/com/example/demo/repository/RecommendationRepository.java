package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Recommendation;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation,Long>{
    List<Recommendation> findByUserIdAndGeneratedAtBetween(Long userId,LocalDateTime start,LocalDateTime end);
    List<Recommendation> findByUserIdOrderByGeneratedAtDesc(Long userId);
}
