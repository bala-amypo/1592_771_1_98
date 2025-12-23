// package com.example.demo.service.impl;

// import java.util.Arrays;
// import java.util.List;

// import org.springframework.stereotype.Service;

// import com.example.demo.entity.Course;
// import com.example.demo.entity.MicroLesson;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.exception.ValidationException;
// import com.example.demo.repository.CourseRepository;
// import com.example.demo.repository.MicroLessonRepository;
// import com.example.demo.service.LessonService;

// import jakarta.transaction.Transactional;

// @Service
// @Transactional
// public class LessonServiceImpl implements LessonService {

//     private final MicroLessonRepository microLessonRepository;
//     private final CourseRepository courseRepository;

//     private final List<String> allowedContentTypes = Arrays.asList("VIDEO", "ARTICLE", "QUIZ", "INTERACTIVE");
//     private final List<String> allowedDifficulties = Arrays.asList("BEGINNER", "INTERMEDIATE", "ADVANCED");

//     public LessonServiceImpl(MicroLessonRepository microLessonRepository, CourseRepository courseRepository) {
//         this.microLessonRepository = microLessonRepository;
//         this.courseRepository = courseRepository;
//     }

//     @Override
//     public MicroLesson addLesson(Long courseId, MicroLesson lesson) {
//         Course course = courseRepository.findById(courseId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Course not found with id:" + courseId));

//         validateLesson(lesson);

//         lesson.setCourse(course);
//         return microLessonRepository.save(lesson);
//     }

//     @Override
//     public MicroLesson updateLesson(Long lessonId, MicroLesson lesson) {
//         MicroLesson existing = microLessonRepository.findById(lessonId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + lessonId));

//         validateLesson(lesson);

//         existing.setTitle(lesson.getTitle());
//         existing.setDurationMinutes(lesson.getDurationMinutes());
//         existing.setContentType(lesson.getContentType());
//         existing.setDifficulty(lesson.getDifficulty());
//         existing.setTags(lesson.getTags());
//         existing.setPublishDate(lesson.getPublishDate());

//         return microLessonRepository.save(existing);
//     }

//     @Override
//     public List<MicroLesson> findLessonsByFilters(String tags, String difficulty, String contentType) {
//         if (difficulty != null && !allowedDifficulties.contains(difficulty.toUpperCase())) {
//             throw new ValidationException("Difficulty must be one of: " + allowedDifficulties);
//         }

//         // Validate content type
//         if (contentType != null && !allowedContentTypes.contains(contentType.toUpperCase())) {
//             throw new ValidationException("Content type must be one of: " + allowedContentTypes);
//         }

//         return microLessonRepository.findByFilters(tags, difficulty, contentType);

//     }

//     @Override
//     public MicroLesson getLesson(Long lessonId) {

//         return microLessonRepository.findById(lessonId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Lesson not found with id: " + lessonId));
//     }

//     private void validateLesson(MicroLesson lesson) {
//         if (lesson.getDurationMinutes() == null || lesson.getDurationMinutes() < 1
//                 || lesson.getDurationMinutes() > 15) {
//             throw new ValidationException("Lesson duration must be between 1 and 15 minutes");
//         }
//         if (!allowedContentTypes.contains(lesson.getContentType().toUpperCase())) {
//             throw new ValidationException("Content type must be one of: " + allowedContentTypes);
//         }
//         if (!allowedDifficulties.contains(lesson.getDifficulty().toUpperCase())) {
//             throw new ValidationException("Difficulty must be one of: " + allowedDifficulties);
//         }
//     }
// }





package com.example.demo.service.impl;

import com.example.demo.model.Course;
import com.example.demo.model.MicroLesson;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.MicroLessonRepository;

import java.util.List;

public class LessonServiceImpl {

    private final MicroLessonRepository repo;
    private final CourseRepository courseRepo;

    public LessonServiceImpl(MicroLessonRepository r, CourseRepository c) {
        this.repo = r;
        this.courseRepo = c;
    }

    public MicroLesson addLesson(Long courseId, MicroLesson lesson) {
        Course c = courseRepo.findById(courseId).orElseThrow(RuntimeException::new);
        lesson.setCourse(c);
        return repo.save(lesson);
    }

    public MicroLesson updateLesson(Long id, MicroLesson updated) {
        MicroLesson m = repo.findById(id).orElseThrow(RuntimeException::new);
        m.setTitle(updated.getTitle());
        m.setDifficulty(updated.getDifficulty());
        m.setContentType(updated.getContentType());
        return repo.save(m);
    }

    public MicroLesson getLesson(Long id) {
        return repo.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<MicroLesson> findLessonsByFilters(String t, String d, String c) {
        return repo.findByFilters(t, d, c);
    }
}
