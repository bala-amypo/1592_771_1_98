package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CourseService;

import jakarta.validation.ValidationException;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;

    public Course createCourse(Course course, Long instructorId){
        User instructor = userRepository.findById(instructorId)
        .orElseThrow(()->new ResourceNotFoundException("Instructor not found with id "+instructorId));

        if(!instructor.getRole().equals("INSTRUCTOR") && !instructor.getRole().equals("ADMIN"))
        {
            throw new ValidationException("Only INSTRUCTOR or ADMIN create courses");
        }
        boolean exists = courseRepository.existsByTitleAndInstructorId(course.getTitle(), instructorId);
        if(exists){
            throw new ValidationException("Course with same title already exists for this instructor");

        }
        course.setInstructor(instructor);
        return courseRepository.save(course);
    }

    public 	Course updateCourse(Long courseId, Course updatedCourse)
    {
        Course existingCourse=courseRepository.findById(courseId)
        .orElseThrow(()-> new ResourceNotFoundException("Course not found with id:"+courseId));

        Long instructorId=existingCourse.getInstructor().getId();

        if(!existingCourse.getTitle().equals(updatedCourse.getTitle()) && courseRepository.existsByTitleAndInstructorId(updatedCourse.getTitle(), instructorId))
        {
            throw new ValidationException("Course with same title already exists for this instructor");
        }
        
        existingCourse.setTitle(updatedCourse.getTitle());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setCategory(updatedCourse.getCategory());

        return courseRepository.save(existingCourse);
    }

    public Course getCourse(Long courseId){
        return courseRepository.findById(courseId)
        .orElseThrow(()-> new ResourceNotFoundException("Course not found with id:"+courseId));
    }

    public 	List<Course> listCoursesByInstructor(Long instructorId)
    {
        return courseRepository.findByInstructorId(instructorId);
    }
}