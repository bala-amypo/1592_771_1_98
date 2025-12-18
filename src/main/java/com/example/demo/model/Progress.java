package com.example.demo.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "progress")
public class Progress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @JsonIgnore
    @NotNull
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "microLessonId")
    @JsonIgnore
    @NotNull
    private MicroLesson microLesson;

    private String status;

    @Min(value = 0)
    @Max(value = 100)
    private Integer progressPercent;

    private LocalDateTime lastAccessedAt;

    private Double score;

    @PrePersist
    protected void onCreate()
    {
        this.lastAccessedAt=LocalDateTime.now();
    }

    public Progress(){}

    public Progress(User user, MicroLesson microLesson, String status, Integer progressPercent,
            LocalDateTime lastAccessedAt, Double score) {
        this.user = user;
        this.microLesson = microLesson;
        this.status = status;
        this.progressPercent = progressPercent;
        this.lastAccessedAt = lastAccessedAt;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MicroLesson getMicroLesson() {
        return microLesson;
    }

    public void setMicroLesson(MicroLesson microLesson) {
        this.microLesson = microLesson;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(status.equals("COMPLETED"))
        {
            setProgressPercent(100);
        }
        this.status = status;
    }

    public Integer getProgressPercent() {
        return progressPercent;
    }

    public void setProgressPercent(Integer progressPercent) {
        this.progressPercent = progressPercent;
    }

    public LocalDateTime getLastAccessedAt() {
        return lastAccessedAt;
    }

    public void setLastAccessedAt(LocalDateTime lastAccessedAt) {
        this.lastAccessedAt = lastAccessedAt;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    
}
