package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "progress",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "micro_lesson_id"})
        }
)
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
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "micro_lesson_id", nullable = false)
    @JsonIgnore
    private MicroLesson microLesson;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "NOT_STARTED|IN_PROGRESS|COMPLETED", message = "Status must be NOT_STARTED, IN_PROGRESS, or COMPLETED")
    @Builder.Default
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String status = "NOT_STARTED";

    @NotNull(message = "Progress percent is required")
    @Min(value = 0, message = "Progress percent cannot be less than 0")
    @Max(value = 100, message = "Progress percent cannot be more than 100")
    @Builder.Default
    @Column(nullable = false)
    private Integer progressPercent = 0;

    @Column(nullable = false)
    private LocalDateTime lastAccessedAt;

    @Column(precision = 5, scale = 2, nullable = true)
    private BigDecimal score;

    @Column(nullable = true)
    private LocalDateTime completedAt;

    // Must be public for test cases to call directly
    @PrePersist
    public void prePersist() {
        this.lastAccessedAt = LocalDateTime.now();

        if (this.status == null) this.status = "NOT_STARTED";
        if (this.progressPercent == null) this.progressPercent = 0;

        if ("COMPLETED".equalsIgnoreCase(this.status)) {
            this.progressPercent = 100;
            if (this.completedAt == null) {
                this.completedAt = LocalDateTime.now();
            }
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

    public void setProgressPercent(Integer progressPercent) {
        if (progressPercent == null) progressPercent = 0;
        if (progressPercent < 0) progressPercent = 0;
        if (progressPercent > 100) progressPercent = 100;
        this.progressPercent = progressPercent;

        if (this.progressPercent == 100) {
            this.status = "COMPLETED";
            if (this.completedAt == null) {
                this.completedAt = LocalDateTime.now();
            }
        }
    }
}







// package com.example.demo.model;

// import jakarta.persistence.*;
// import lombok.*;

// import java.math.BigDecimal;
// import java.time.LocalDateTime;

// @Entity
// @Table(
//         name = "progress",
//         uniqueConstraints = {
//                 @UniqueConstraint(columnNames = {"user_id", "micro_lesson_id"})
//         }
// )
// @Data
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
// public class Progress {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "user_id", nullable = false)
//     private User user;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "micro_lesson_id", nullable = false)
//     private MicroLesson microLesson;

//     private String status = "NOT_STARTED";

//     private Integer progressPercent;

//     private LocalDateTime lastAccessedAt;

//     private BigDecimal score;

//     private LocalDateTime completedAt;

//     @PrePersist
//     public void prePersist() {
//         this.lastAccessedAt = LocalDateTime.now();
//     }
// }
