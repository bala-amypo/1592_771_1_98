package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;


@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("/courses/{instructorId}")
    public ResponseEntity<Course> createCourse(@PathVariable Long instructorId,@RequestBody Course course)
    {
        Course createdCourse=courseService.createCourse(course, instructorId);
        return new ResponseEntity<>(createdCourse,HttpStatus.CREATED);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long courseId,@RequestBody Course course)
    {
        return ResponseEntity.ok(courseService.updateCourse(courseId, course));
    }

    @GetMapping("{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable Long courseId)
    {
        return ResponseEntity.ok(courseService.getCourse(courseId));
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<Course>> getCourseByInstructor(@PathVariable Long instructorId)
    {
        return ResponseEntity.ok(courseService.listCoursesByInstructor(instructorId));
    }
}