package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "micro_lessons")
public class MicroLesson {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    @JsonIgnore
    @NotNull
    private Course course;
    
    @NotBlank
    private String title;

    @Min(value = 1)
    @Max(value = 15)
    @Column(nullable = false)
    private Integer durationMinutes;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private String difficulty;

    private String tags;
    private LocalDate publishDate;

    @OneToMany(mappedBy = "microLesson",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Progress> progresses=new ArrayList<>();

    public MicroLesson(){}


    public MicroLesson(Course course, String title, Integer durationMinutes, String contentType, String difficulty,
            String tags, LocalDate publishDate) {
        this.course = course;
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.contentType = contentType;
        this.difficulty = difficulty;
        this.tags = tags;
        this.publishDate = publishDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        if(durationMinutes<1 || durationMinutes>15)
        {
            throw new IllegalArgumentException("Duration must be between 1 and 15 minutes.");
        }
        this.durationMinutes = durationMinutes;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public List<Progress> getProgresses()
    {
        return progresses;
    }

    public void setProgresses(List<Progress> progresses)
    {
        this.progresses=progresses;
        if(progresses!=null)
        {
            for(Progress p:progresses)
            {
                p.setMicroLesson(this);
            }
        }
    }
}
