package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Course;

public interface CourseService {
    Course createCourse(Course course,Long instructorId);
    Course updateCourse(Long courseId,Course course);
    List<Course> listCoursesByInstructor(Long instructorId);
    Course getCourse(Long courseId);
}
