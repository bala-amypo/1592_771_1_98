package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.MicroLesson;
import com.example.demo.model.Progress;
import com.example.demo.model.User;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProgressService;

@Service
public class ProgressServiceImpl implements ProgressService{
    
    @Autowired
    ProgressRepository progressRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MicroLessonRepository microLessonRepository;

    public Progress recordProgress(Long userId,Long lessonId,Progress progress)
    {
        User user=userRepository.findById(userId)
        .orElseThrow(()-> new ResourceNotFoundException("User not found with id:"+userId));

        MicroLesson lesson=microLessonRepository.findById(lessonId)
        .orElseThrow(()-> new ResourceNotFoundException("Lesson not found with id:"+lessonId));

        if(progress.getProgressPercent() < 0 || progress.getProgressPercent()>100)
        {
            throw new IllegalArgumentException("Progress percent must be between 0 and 100");
        }

        if("COMPLETED".equalsIgnoreCase(progress.getStatus()) && progress.getProgressPercent()!=100)
        {
            progress.setProgressPercent(100);
        }

        Optional<Progress> existingProgress = progressRepository.findByUserIdAndMicroLessonId(userId, lessonId);
    
        Progress toSave;
        if(existingProgress.isPresent())
        {
            toSave = existingProgress.get();
            toSave.setProgressPercent(progress.getProgressPercent());
            toSave.setStatus(progress.getStatus());
            toSave.setScore(progress.getScore());
            toSave.setLastAccessedAt(LocalDateTime.now());
        }
        else{
            toSave=new Progress(user,lesson,progress.getStatus(),progress.getProgressPercent(),LocalDateTime.now(),progress.getScore());
        }
        return progressRepository.save(toSave);
    }

    public Progress getProgress(Long userId,Long lessonId)
    {
        return progressRepository.findByUserIdAndMicroLessonId(userId, lessonId)
        .orElseThrow(()-> new ResourceNotFoundException("Progress not found for user and lesson"));
    }

    public List<Progress> getUserProgress(Long userId)
    {
        return progressRepository.findByUserIdOrderByLastAccessedAtDesc(userId);
    }
}
