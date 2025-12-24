// package com.example.demo.service.impl;

// import java.util.List;

// import org.springframework.stereotype.Service;

// import com.example.demo.entity.Course;
// import com.example.demo.entity.User;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.exception.ValidationException;
// import com.example.demo.repository.CourseRepository;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.CourseService;

// import jakarta.transaction.Transactional;

// @Service
// @Transactional
// public class CourseServiceImpl implements CourseService {

//     private final CourseRepository courseRepository;
//     private final UserRepository userRepository;

//     public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
//         this.courseRepository = courseRepository;
//         this.userRepository = userRepository;
//     }

//     @Override
//     public Course createCourse(Course course, Long instructorId) {

//         User instructor = userRepository.findById(instructorId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Instructor not found with id " + instructorId));

//         if (!instructor.getRole().equalsIgnoreCase("INSTRUCTOR") && !instructor.getRole().equalsIgnoreCase("ADMIN")) {
//             throw new ValidationException("Only INSTRUCTOR or ADMIN create courses");
//         }
//         boolean exists = courseRepository.existsByTitleAndInstructorId(course.getTitle(), instructorId);
//         if (exists) {
//             throw new ValidationException("Course with same title already exists for this instructor");

//         }
//         course.setInstructor(instructor);
//         return courseRepository.save(course);
//     }

//     @Override
//     public Course updateCourse(Long courseId, Course updatedCourse) {
//         Course existingCourse = courseRepository.findById(courseId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Course not found with id:" + courseId));

//         Long instructorId = existingCourse.getInstructor().getId();

//         if (!existingCourse.getTitle().equals(updatedCourse.getTitle())
//                 && courseRepository.existsByTitleAndInstructorId(updatedCourse.getTitle(), instructorId)) {
//             throw new ValidationException("Course with this title already exists for this instructor");
//         }

//         existingCourse.setTitle(updatedCourse.getTitle());
//         existingCourse.setDescription(updatedCourse.getDescription());
//         existingCourse.setCategory(updatedCourse.getCategory());

//         return courseRepository.save(existingCourse);
//     }

//     @Override
//     public Course getCourse(Long courseId) {
//         return courseRepository.findById(courseId)
//                 .orElseThrow(() -> new ResourceNotFoundException("Course not found with id:" + courseId));


//     }

//     @Override
//     public List<Course> listCoursesByInstructor(Long instructorId) {
//         User instructor  = userRepository.findById(instructorId)
//         .orElseThrow(()-> new ResourceNotFoundException("User not found with id "+instructorId));

        
//         if (!instructor.getRole().equalsIgnoreCase("INSTRUCTOR") &&
//             !instructor.getRole().equalsIgnoreCase("ADMIN")) {
//             throw new ValidationException("User with id " + instructorId + " is not an instructor or admin");
//         }
//         return courseRepository.findByInstructorId(instructorId);
//     }
// }





package com.example.demo.service.impl;

import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseServiceImpl(CourseRepository courseRepository,
                             UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Course createCourse(Course course, Long instructorId) {
        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        if (courseRepository.existsByTitleAndInstructorId(course.getTitle(), instructorId)) {
            throw new RuntimeException("Duplicate course");
        }

        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long courseId, Course course) {
        Course existing = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        existing.setTitle(course.getTitle());
        existing.setDescription(course.getDescription());
        existing.setCategory(course.getCategory());

        return courseRepository.save(existing);
    }

    @Override
    public List<Course> listCoursesByInstructor(Long instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }

    @Override
    public Course getCourse(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }
}
