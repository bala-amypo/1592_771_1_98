// package com.example.demo.controller;

// import java.util.List;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.entity.MicroLesson;
// import com.example.demo.service.LessonService;

// import io.swagger.v3.oas.annotations.tags.Tag;
// import jakarta.validation.Valid;

// @RestController
// @RequestMapping("/lessons")
// @Tag(name = "Lesson Management")

// public class LessonController {

//     private final LessonService lessonService;

//     public LessonController(LessonService lessonService) {
//         this.lessonService = lessonService;
//     }

//     @PostMapping("/course/{courseId}")
//     public ResponseEntity<MicroLesson> addLesson(@PathVariable Long courseId, @Valid @RequestBody MicroLesson lesson) {
//         return ResponseEntity.ok(lessonService.addLesson(courseId, lesson));
//     }

//     @PutMapping("/{lessonId}")
//     public ResponseEntity<MicroLesson> updateLesson(@PathVariable Long lessonId,
//             @Valid @RequestBody MicroLesson lesson) {
//         return ResponseEntity.ok(lessonService.updateLesson(lessonId, lesson));
//     }

//     @GetMapping("/search")
//     public ResponseEntity<?> searchLessons(@RequestParam(required = false) String tags,
//             @RequestParam(required = false) String difficulty, @RequestParam(required = false) String contentType) {
//         List<MicroLesson> lessons = lessonService.findLessonsByFilters(tags, difficulty, contentType);
//         if (lessons.isEmpty()) {
//             return ResponseEntity.status(404)
//                     .body("No lessons found for the given filters");
//         }

//         return ResponseEntity.ok(lessons);
//     }

//     @GetMapping("/{lessonId}")
//     public ResponseEntity<MicroLesson> getLesson(@PathVariable Long lessonId) {
//         return ResponseEntity.ok(lessonService.getLesson(lessonId));
//     }
// }






package com.example.demo.controller;

import com.example.demo.model.MicroLesson;
import com.example.demo.service.LessonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/lessons")
@Tag(name = "Lesson Management")
@SecurityRequirement(name = "bearerAuth")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping("/course/{courseId}")
    public ResponseEntity<MicroLesson> addLesson(
            @PathVariable Long courseId,
            @RequestBody MicroLesson lesson) {
        return ResponseEntity.ok(lessonService.addLesson(courseId, lesson));
    }

    @PutMapping("/{lessonId}")
    public ResponseEntity<MicroLesson> updateLesson(
            @PathVariable Long lessonId,
            @RequestBody MicroLesson lesson) {
        return ResponseEntity.ok(lessonService.updateLesson(lessonId, lesson));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MicroLesson>> searchLessons(
            @RequestParam(required = false) String tags,
            @RequestParam(required = false) String difficulty,
            @RequestParam(required = false) String contentType) {

        return ResponseEntity.ok(
                lessonService.findLessonsByFilters(tags, difficulty, contentType)
        );
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<MicroLesson> getLesson(@PathVariable Long lessonId) {
        return ResponseEntity.ok(lessonService.getLesson(lessonId));
    }
}
