package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Course;
import com.example.demo.model.MicroLesson;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.MicroLessonRepository;
import com.example.demo.service.LessonService;

import jakarta.validation.ValidationException;

@Service
public class LessonServiceImpl implements LessonService{
    
    @Autowired
    MicroLessonRepository microLessonRepository;

    @Autowired
    CourseRepository courseRepository;

    public MicroLesson addLesson(Long courseId,MicroLesson lesson)
    {
        Course course=courseRepository.findById(courseId)
        .orElseThrow(()-> new ResourceNotFoundException("Course not found with id:"+courseId));

        if(lesson.getDurationMinutes()==null || lesson.getDurationMinutes()<1 || lesson.getDurationMinutes()>15)
        {
            throw new ValidationException("Lesson duration must be between 1 and 15 minutes");
        }

        lesson.setCourse(course);
        return microLessonRepository.save(lesson);
    }

    public MicroLesson updateLesson(Long lessonId,MicroLesson lesson){
        MicroLesson existing=microLessonRepository.findById(lessonId)
        .orElseThrow(()-> new ResourceNotFoundException("Lesson not found"));

        existing.setTitle(lesson.getTitle());
        existing.setDurationMinutes(lesson.getDurationMinutes());
        existing.setContentType(lesson.getContentType());
        existing.setDifficulty(lesson.getDifficulty());
        existing.setTags(lesson.getTags());
        existing.setPublishDate(lesson.getPublishDate());

        return microLessonRepository.save(existing);
    }

    public List<MicroLesson> findLessonsByFilters(String tags,String difficulty,String contentType)
    {
        return microLessonRepository.findByFilters(tags, difficulty, contentType);
    }

    public MicroLesson getLesson(Long lessonId)
    {
        return microLessonRepository.findById(lessonId)
        .orElseThrow(()-> new ResourceNotFoundException("Lesson not found"));
    }
}
