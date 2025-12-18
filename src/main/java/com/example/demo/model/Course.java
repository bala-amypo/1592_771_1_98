package com.example.demo.model;

import java.time.LocalDateTime;
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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true,nullable = false)
    private String title;

    @Column(nullable = true)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructorId")
    @JsonIgnore
    @NotNull
    private User instructor;

    private String category;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate()
    {
        this.createdAt=LocalDateTime.now();
    }

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MicroLesson> microLessons=new ArrayList<>();

    public Course(){}

    public Course(String title, String description, User instructor, String category, LocalDateTime createdAt) {
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.category = category;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        if(instructor.getRole()==null || (!instructor.getRole().equals("INSTRUCTOR") && !instructor.getRole().equals("ADMIN")))
        {
            throw new IllegalArgumentException("Instructor must have a role of INSTRUCTOR or ADMIN");
        }
        this.instructor = instructor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<MicroLesson> getMicroLessons()
    {
        return microLessons;
    }

    public void setMicroLessons(List<MicroLesson> microLessons)
    {
        this.microLessons=microLessons;
        if(microLessons!=null)
        {
            for(MicroLesson l:microLessons)
            {
                l.setCourse(this);
            }
        }
    }

}
