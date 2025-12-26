package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "micro_lessons")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MicroLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnore
    private Course course;

    @NotBlank(message = "Lesson title is required")
    @Size(max = 150, message = "Title must be at most 150 characters")
    @Column(nullable = false, length = 150)
    private String title;

    @Positive
    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 minute")
    @Max(value = 15, message = "Duration must not exceed 15 minutes")
    @Column(nullable = false)
    private Integer durationMinutes;

    @NotBlank(message = "Content type is required")
    @Pattern(regexp = "VIDEO|ARTICLE|QUIZ|INTERACTIVE", message = "Content type must be VIDEO, ARTICLE, QUIZ, or INTERACTIVE")
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String contentType;

    @NotBlank(message = "Difficulty is required")
    @Pattern(regexp = "BEGINNER|INTERMEDIATE|ADVANCED", message = "Difficulty must be BEGINNER, INTERMEDIATE, or ADVANCED")
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String difficulty;

    @Size(max = 500, message = "Tags must be at most 500 characters")
    @Column(length = 500)
    private String tags;

    @NotNull(message = "Publish date is required")
    @Column(nullable = false)
    private LocalDate publishDate;

    @OneToMany(mappedBy = "microLesson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @Builder.Default
    private List<Progress> progresses = new ArrayList<>();

    // **Must be public for test cases**
    @PrePersist
    public void prePersist() {
        if (this.title != null) this.title = this.title.trim();
        if (this.contentType != null) this.contentType = this.contentType.trim().toUpperCase();
        if (this.difficulty != null) this.difficulty = this.difficulty.trim().toUpperCase();
        if (this.tags != null) this.tags = this.tags.trim();
    }
}









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
