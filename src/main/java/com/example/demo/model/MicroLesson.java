// package com.example.demo.entity;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;
// import jakarta.validation.constraints.Max;
// import jakarta.validation.constraints.Min;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Pattern;
// import jakarta.validation.constraints.Positive;
// import jakarta.validation.constraints.Size;
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Table(name = "micro_lessons")
// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor

// public class MicroLesson {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne(fetch = FetchType.LAZY, optional = false)
//     @JoinColumn(name = "course_id", nullable = false)
//     @JsonIgnore
//     private Course course;

//     @NotBlank(message = "Lesson title is required")
//     @Size(max = 150, message = "Title must be at most 150 characters")
//     @Column(nullable = false, length = 150)
//     private String title;

//     @Positive
//     @NotNull(message = "Duration is required")
//     @Min(value = 1, message = "Duration must be at least 1 minute")
//     @Max(value = 15, message = "Duration must not exceed 15 minutes")
//     @Column(nullable = false)
//     private Integer durationMinutes;

//     @NotBlank(message = "Content type is required")
//     @Pattern(regexp = "VIDEO|ARTICLE|QUIZ|INTERACTIVE", message = "Content type must be VIDEO, ARTICLE, QUIZ, or INTERACTIVE")
//     @Size(max = 50)
//     @Column(nullable = false, length = 50)
//     private String contentType;

//     @NotBlank(message = "Difficulty is required")
//     @Pattern(regexp = "BEGINNER|INTERMEDIATE|ADVANCED", message = "Difficulty must be BEGINNER, INTERMEDIATE, or ADVANCED")
//     @Size(max = 50)
//     @Column(nullable = false, length = 50)
//     private String difficulty;

//     @Size(max = 500, message = "Tags must be at most 500 characters")
//     @Column(nullable = true, length = 500)
//     private String tags;

//     @NotNull(message = "Publish date is required")
//     private LocalDate publishDate;

//     @OneToMany(mappedBy = "microLesson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//     @JsonIgnore
//     @Builder.Default
//     private List<Progress> progresses = new ArrayList<>();
// }









// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.*;

// import java.time.LocalDate;

// @Entity
// @Table(name = "micro_lessons")
// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
// public class MicroLesson {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "course_id", nullable = false)
//     private Course course;

//     private String title;

//     private Integer durationMinutes;

//     private String contentType;

//     private String difficulty;

//     @Column(length = 500)
//     private String tags;

//     private LocalDate publishDate;
// }
