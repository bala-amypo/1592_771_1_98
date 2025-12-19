package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.MicroLesson;
import com.example.demo.service.LessonService;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    
    @Autowired
    LessonService lessonService;

    @PostMapping("/courses/{courseId}")
    public ResponseEntity<MicroLesson> addLesson(@PathVariable Long courseId,@RequestBody MicroLesson lesson)
    {
        return ResponseEntity.ok(lessonService.addLesson(courseId, lesson));
    }

    @PutMapping("/{lessonId}")
    public ResponseEntity<MicroLesson> updateLesson(@PathVariable Long lessonId,@RequestBody MicroLesson lesson)
    {
        return ResponseEntity.ok(lessonService.updateLesson(lessonId, lesson));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MicroLesson>> searchLessons(@RequestParam(required = false) String tags,@RequestParam (required = false) String difficulty,@RequestParam (required = false) String contentType)
    {
        return ResponseEntity.ok(lessonService.findLessonsByFilters(tags, difficulty, contentType));

    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<MicroLesson> getLesson(@PathVariable Long lessonId)
    {
        return ResponseEntity.ok(lessonService.getLesson(lessonId));
    }
}
