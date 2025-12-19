package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Progress;
import com.example.demo.service.ProgressService;


@RestController
@RequestMapping("/progress")
public class ProgressController {
    @Autowired
    ProgressService progressService;

    @PostMapping("/{lessonId}")
    public ResponseEntity<Progress> recordProgress(@RequestParam Long userId,@RequestParam Long lessonId,@RequestBody Progress progress)
    {
        Progress updateProgress=progressService.recordProgress(userId, lessonId,progress);
        return ResponseEntity.ok(updateProgress);
    }

    @GetMapping("/lesson/{lessonId}")
    public ResponseEntity<Progress> getProgress(@RequestParam Long userId,@RequestParam Long lessonId)
    {
        Progress progress=progressService.getProgress(userId, lessonId);
        return ResponseEntity.ok(progress);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Progress>> getUserProgress(@PathVariable Long userId)
    {
        List<Progress> progressList=progressService.getUserProgress(userId);
        return ResponseEntity.ok(progressList);
    }
}
