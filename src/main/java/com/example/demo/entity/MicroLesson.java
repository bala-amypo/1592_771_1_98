package com.example.demo.entity;

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
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotNull(message = "Course must be provided")
    private Course course;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false)
    private String title;

    @NotNull
    @Positive
    @Min(value = 1)
    @Max(value = 15)
    @Column(nullable = false)
    private Integer durationMinutes;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String contentType;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false)
    private String difficulty;

    @Size(max = 500)
    @Column(nullable = true)
    private String tags;

    @NotNull
    private LocalDate publishDate;

    @OneToMany(mappedBy = "microLesson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @Builder.Default
    private List<Progress> progresses = new ArrayList<>();
}



