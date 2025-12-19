package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Progress;

@Repository
public interface ProgressRepository extends JpaRepository<Progress,Long> {
    Optional<Progress> findByUserIdAndMicroLessonId(Long userId,Long lessonId);
    List<Progress> findByUserIdOrderByLastAccessedAtDesc(Long userId);
}
