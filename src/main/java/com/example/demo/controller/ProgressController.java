
package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Progress;
import com.example.demo.service.ProgressService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/progress")
@Tag(name = "Progress Tracking")
public class ProgressController {
    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping("/{lessonId}")
    public ResponseEntity<Progress> recordProgress(@RequestParam Long userId, @PathVariable Long lessonId,
            @Valid @RequestBody Progress progress) {
        Progress updateProgress = progressService.recordProgress(userId, lessonId, progress);
        return ResponseEntity.ok(updateProgress);
    }

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<Progress> getProgress(@RequestParam Long userId, @PathVariable Long lessonId) {
        Progress progress = progressService.getProgress(userId, lessonId);
        return ResponseEntity.ok(progress);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Progress>> getUserProgress(@PathVariable Long userId) {
        List<Progress> progressList = progressService.getUserProgress(userId);
        return ResponseEntity.ok(progressList);
    }
}