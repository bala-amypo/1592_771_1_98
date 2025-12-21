package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "progress", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "user_id", "micro_lesson_id" })
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    @NotNull(message = "User must be provided")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "micro_lesson_id", nullable = false)
    @JsonIgnore
    @NotNull(message = "MicroLesson must be provided")
    private MicroLesson microLesson;

    @NotBlank
    @Builder.Default
    @Size(max = 20)
    @Column(nullable = false)
    private String status = "NOT_STARTED";

    @Min(value = 0)
    @Max(value = 100)
    @NotNull
    @Builder.Default
    @Column(nullable = false)
    private Integer progressPercent = 0;

    @Column(nullable = false)
    private LocalDateTime lastAccessedAt;

    @Column(precision = 5, scale = 2,nullable = true)
    private BigDecimal score;

    @Column(nullable = true)
    private LocalDateTime completedAt;

    @PrePersist
    protected void onCreate() {
        this.lastAccessedAt = LocalDateTime.now();
        if (this.status == null)
            this.status = "NOT_STARTED";
        if (this.progressPercent == null)
            this.progressPercent = 0;
        if ("COMPLETED".equalsIgnoreCase(this.status)) {
            this.progressPercent = 100;
            this.completedAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.lastAccessedAt = LocalDateTime.now();
        if ("COMPLETED".equalsIgnoreCase(this.status)) {
            this.progressPercent = 100;
            if (this.completedAt == null) {
                this.completedAt = LocalDateTime.now();
            }
        }
    }

    public void setStatus(String status) {
        this.status = status;
        if ("COMPLETED".equalsIgnoreCase(status)) {
            this.progressPercent = 100;
            if (this.completedAt == null) {
                this.completedAt = LocalDateTime.now();
            }

        }
    }

    public String getStatus() {
        return status;
    }

    public void setProgressPercent(Integer progressPercent) {
        if (progressPercent < 0)
            progressPercent = 0;
        if (progressPercent > 100)
            progressPercent = 100;
        this.progressPercent = progressPercent;
        if (this.progressPercent == 100) {
            this.status = "COMPLETED";
            if (this.completedAt == null) {
                this.completedAt = LocalDateTime.now();
            }
        }
    }

    public Integer getProgressPercent() {
        return progressPercent;
    }
}

