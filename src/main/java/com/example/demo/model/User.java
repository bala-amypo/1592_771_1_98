// package com.example.demo.model;

// import java.time.LocalDateTime;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.PrePersist;
// import jakarta.persistence.Table;
// import jakarta.validation.constraints.Email;

// @Entity
// @Table(name = "users")
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String fullName;

//     @Column(unique = true)
//     @Email
//     private String email;

//     private String password;

//     private String role;

//     @Column(nullable = true)
//     private String preferredLearningStyle;

//     private LocalDateTime createdAt;

//     @PrePersist
//     protected void onCreate()
//     {
//         if(role==null)
//         {
//             role="LEARNER";
//         }
//         this.createdAt=LocalDateTime.now();
//     }
    
//     public User() {
//     }

//     public User(String fullName, String email, String password, String role, String preferredLearningStyle,
//             LocalDateTime createdAt) {
//         this.fullName = fullName;
//         this.email = email;
//         this.password = password;
//         this.role = role;
//         this.preferredLearningStyle = preferredLearningStyle;
//         this.createdAt = createdAt;
//     }

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getFullName() {
//         return fullName;
//     }

//     public void setFullName(String fullName) {
//         this.fullName = fullName;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getPassword() {
//         return password;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

//     public String getRole() {
//         return role;
//     }

//     public void setRole(String role) {
//         this.role = role;
//     }

//     public String getPreferredLearningStyle() {
//         return preferredLearningStyle;
//     }

//     public void setPreferredLearningStyle(String preferredLearningStyle) {
//         this.preferredLearningStyle = preferredLearningStyle;
//     }

//     public LocalDateTime getCreatedAt() {
//         return createdAt;
//     }

//     public void setCreatedAt(LocalDateTime createdAt) {
//         this.createdAt = createdAt;
//     }

// }
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true,nullable = false)
    @NotBlank
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role="LEARNER";

    @Column(nullable = true)
    private String preferredLearningStyle;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate()
    {
        this.createdAt=LocalDateTime.now();
    }
    
    @OneToMany(mappedBy = "instructor",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Course> courses=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Progress> progresses=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Recommendation> recommendations=new ArrayList<>();


    public User() {}

    public User(String fullName, String email, String password, String role, String preferredLearningStyle,
            LocalDateTime createdAt) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.preferredLearningStyle = preferredLearningStyle;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if(role==null)
        {
            this.role="LEARNER";
        }
        else
        {
            if(!role.equals("LEARNER") && !role.equals("INSTRUCTOR") && !role.equals("ADMIN"))
            {
                throw new IllegalArgumentException("Role must be LEARNER,INSTRUCTOR or Admin");
            }
            this.role = role;
        }
    }

    public String getPreferredLearningStyle() {
        return preferredLearningStyle;
    }

    public void setPreferredLearningStyle(String preferredLearningStyle) {
        this.preferredLearningStyle = preferredLearningStyle;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Course> getCourses()
    {
        return courses;
    }

    public void setCourses(List<Course> courses)
    {
        this.courses=courses;
        if(courses!=null)
        {
            for(Course course:courses)
            {
                course.setInstructor(this);
            }
        }
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
                p.setUser(this);
            }
        }
    }

    public List<Recommendation> getRecommendations()
    {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations)
    {
        this.recommendations=recommendations;
        if(recommendations!=null)
        {
            for(Recommendation r:recommendations)
            {
                r.setUser(this);
            }
        }
    }

}