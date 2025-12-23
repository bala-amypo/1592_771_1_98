// package com.example.demo.service.impl;

// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.stereotype.Service;

// import com.example.demo.entity.MicroLesson;
// import com.example.demo.entity.Progress;
// import com.example.demo.entity.User;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.MicroLessonRepository;
// import com.example.demo.repository.ProgressRepository;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.ProgressService;

// import jakarta.transaction.Transactional;

// @Service
// @Transactional
// public class ProgressServiceImpl implements ProgressService {

//     private final ProgressRepository progressRepository;
//     private final UserRepository userRepository;
//     private final MicroLessonRepository microLessonRepository;

//     public ProgressServiceImpl(ProgressRepository progressRepository, UserRepository userRepository,
//             MicroLessonRepository microLessonRepository) {
//         this.progressRepository = progressRepository;
//         this.userRepository = userRepository;
//         this.microLessonRepository = microLessonRepository;
//     }

//     @Override
//     public Progress recordProgress(Long userId, Long lessonId, Progress progress) {
//         User user = userRepository.findById(userId)
//                 .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + userId));

//         MicroLesson lesson = microLessonRepository.findById(lessonId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id:" + lessonId));

//         if (progress.getProgressPercent() < 0 || progress.getProgressPercent() > 100) {
//             throw new IllegalArgumentException("Progress percent must be between 0 and 100");
//         }

//         if ("COMPLETED".equalsIgnoreCase(progress.getStatus()) && progress.getProgressPercent() != 100) {
//             progress.setProgressPercent(100);
//         }

//         Optional<Progress> existingProgress = progressRepository.findByUserIdAndMicroLessonId(userId, lessonId);

//         List<String> allowedStatus = List.of("NOT_STARTED", "IN_PROGRESS", "COMPLETED");
//         if (!allowedStatus.contains(progress.getStatus().toUpperCase())) {
//             throw new IllegalArgumentException("Invalid progress status");
//         }
//         Progress toSave;
//         if (existingProgress.isPresent()) {
//             toSave = existingProgress.get();
//             toSave.setProgressPercent(progress.getProgressPercent());
//             toSave.setStatus(progress.getStatus());
//             toSave.setScore(progress.getScore());
//             toSave.setLastAccessedAt(LocalDateTime.now());
//         } else {
//             toSave = Progress.builder()
//                     .user(user)
//                     .microLesson(lesson)
//                     .status(progress.getStatus())
//                     .progressPercent(progress.getProgressPercent())
//                     .score(progress.getScore())
//                     .lastAccessedAt(LocalDateTime.now())
//                     .build();
//         }
//         return progressRepository.save(toSave);
//     }

//     @Override
//     public Progress getProgress(Long userId, Long lessonId) {
//         return progressRepository.findByUserIdAndMicroLessonId(userId, lessonId)
//                 .orElseThrow(() -> new ResourceNotFoundException(
//                         "Progress not found for userId: " + userId + " and lessonId: " + lessonId));
//     }

//     @Override
//     public List<Progress> getUserProgress(Long userId) {
//         return progressRepository.findByUserIdOrderByLastAccessedAtDesc(userId);
//     }

// }





package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProgressServiceImpl {

    private final ProgressRepository repo;
    private final UserRepository userRepo;
    private final MicroLessonRepository lessonRepo;

    public ProgressServiceImpl(ProgressRepository r, UserRepository u, MicroLessonRepository l) {
        this.repo = r;
        this.userRepo = u;
        this.lessonRepo = l;
    }

    public Progress recordProgress(Long userId, Long lessonId, Progress p) {
        User u = userRepo.findById(userId).orElseThrow(RuntimeException::new);
        MicroLesson l = lessonRepo.findById(lessonId).orElseThrow(RuntimeException::new);

        Progress existing = repo.findByUserIdAndMicroLessonId(userId, lessonId).orElse(null);
        if (existing != null) {
            existing.setProgressPercent(p.getProgressPercent());
            existing.setStatus(p.getStatus());
            existing.setScore(p.getScore());
            return repo.save(existing);
        }

        p.setUser(u);
        p.setMicroLesson(l);
        return repo.save(p);
    }

    public List<Progress> getUserProgress(Long userId) {
        return repo.findByUserIdOrderByLastAccessedAtDesc(userId);
    }
}
