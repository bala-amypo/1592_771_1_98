// package com.example.demo.service;

// import java.util.List;

// import com.example.demo.entity.Course;

// public interface CourseService {

//     Course createCourse(Course course, Long instructorId);

//     Course updateCourse(Long courseId, Course course);

//     Course getCourse(Long courseId);

//     List<Course> listCoursesByInstructor(Long instructorId);
// }





package com.example.demo.service;

import com.example.demo.model.Course;

import java.util.List;

public interface CourseService {

    Course createCourse(Course course, Long instructorId);

    Course updateCourse(Long courseId, Course course);

    List<Course> listCoursesByInstructor(Long instructorId);

    Course getCourse(Long courseId);
}
