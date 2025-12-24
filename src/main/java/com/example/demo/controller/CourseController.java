// package com.example.demo.controller;

// import java.util.List;
// import java.util.Map;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.entity.Course;
// import com.example.demo.service.CourseService;

// import io.swagger.v3.oas.annotations.tags.Tag;
// import jakarta.validation.Valid;

// @RestController
// @RequestMapping("/courses")
// @Tag(name = "Course Management")

// public class CourseController {

//     private final CourseService courseService;

//     public CourseController(CourseService courseService) {
//         this.courseService = courseService;
//     }

//     @PostMapping("/{instructorId}")
//     public ResponseEntity<Course> createCourse(@PathVariable Long instructorId, @Valid @RequestBody Course course) {
//         Course createdCourse = courseService.createCourse(course, instructorId);
//         return ResponseEntity.status(201).body(createdCourse);
//     }

//     @PutMapping("/{courseId}")
//     public ResponseEntity<Course> updateCourse(@PathVariable Long courseId, @Valid @RequestBody Course course) {
//         Course updatedCourse = courseService.updateCourse(courseId, course);
//         return ResponseEntity.status(200).body(updatedCourse);
//     }

//     @GetMapping("{courseId}")
//     public ResponseEntity<Course> getCourse(@PathVariable Long courseId) {
//         Course course = courseService.getCourse(courseId);
//         return ResponseEntity.status(200).body(course);
//     }

//     @GetMapping("/instructor/{instructorId}")
//     public ResponseEntity<?> getCourseByInstructor(@PathVariable Long instructorId) {
        
//         List<Course> courses = courseService.listCoursesByInstructor(instructorId);
//         if (courses.isEmpty()) {
//             return ResponseEntity.ok(Map.of(
//                     "message", "Instructor exists but has no courses yet",
//                     "instructorId", instructorId,
//                     "courses", courses
//             ));
//         }
//         return ResponseEntity.status(200).body(courses);
//     }
// }






// package com.example.demo.controller;

// import com.example.demo.model.Course;
// import com.example.demo.service.CourseService;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/courses")
// @Tag(name = "Course Management")
// public class CourseController {

//     private final CourseService courseService;

//     public CourseController(CourseService courseService) {
//         this.courseService = courseService;
//     }

//     @PostMapping
//     public ResponseEntity<Course> createCourse(
//             @RequestBody Course course,
//             @RequestParam Long instructorId) {
//         return ResponseEntity.ok(courseService.createCourse(course, instructorId));
//     }

//     @PutMapping("/{courseId}")
//     public ResponseEntity<Course> updateCourse(
//             @PathVariable Long courseId,
//             @RequestBody Course course) {
//         return ResponseEntity.ok(courseService.updateCourse(courseId, course));
//     }

//     @GetMapping("/instructor/{instructorId}")
//     public ResponseEntity<List<Course>> getCoursesByInstructor(
//             @PathVariable Long instructorId) {
//         return ResponseEntity.ok(courseService.listCoursesByInstructor(instructorId));
//     }

//     @GetMapping("/{courseId}")
//     public ResponseEntity<Course> getCourse(@PathVariable Long courseId) {
//         return ResponseEntity.ok(courseService.getCourse(courseId));
//     }
// }




package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.service.CourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@Tag(name = "Course Management")

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
