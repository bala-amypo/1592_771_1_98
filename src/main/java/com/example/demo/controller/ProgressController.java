package com.example.demo.controller;

import com.example.demo.model.Progress;
import com.example.demo.service.ProgressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RestController
@RequestMapping("/progress")
@Tag(name = "Progress Tracking")
@SecurityRequirement(name = "bearerAuth")
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping("/{lessonId}")
    public ResponseEntity<Progress> recordProgress(
            @PathVariable Long lessonId,
            @RequestParam Long userId,
            @RequestBody Progress progress) {

        return ResponseEntity.ok(
                progressService.recordProgress(userId, lessonId, progress)
        );
    }

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<Progress> getLessonProgress(
            @PathVariable Long lessonId,
            @RequestParam Long userId) {

        return ResponseEntity.ok(
                progressService.getProgress(userId, lessonId)
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Progress>> getUserProgress(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                progressService.getUserProgress(userId)
        );
    }
}
