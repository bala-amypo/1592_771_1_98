package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/courses")
@Tag(name = "Course Management")
@SecurityRequirement(name = "bearerAuth")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(
            @RequestBody Course course,
            @RequestParam Long instructorId) {
        return ResponseEntity.ok(courseService.createCourse(course, instructorId));
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable Long courseId,
            @RequestBody Course course) {
        return ResponseEntity.ok(courseService.updateCourse(courseId, course));
    }

    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<Course>> getCoursesByInstructor(
            @PathVariable Long instructorId) {
        return ResponseEntity.ok(courseService.listCoursesByInstructor(instructorId));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getCourse(courseId));
    }
}

