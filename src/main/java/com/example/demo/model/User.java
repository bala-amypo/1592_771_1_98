package com.example.demo.model;

public class User {
    private long id;
    private String fullName;
    private String email;
    private String password;
    private String role;
    private String preferredLearningStyle;
    private LocalDateTime createdAt;

    public User(){}
    
    public User(String fullName,String email,String password,String role,String preferredLearningStyle,LocalDateTime createdAt)
    {
        this.fullName=fullName;
        this.email=email;
        this.password=password;
        this.role=role;
        this.preferredLearningStyle=preferredLearningStyle;
        this.createdAt=createdAt;
    }

    public void setId(int id)
    {
        this.id=id;
    }
    public long getId()
    {
        return id;
    }
    
    public void setFullName(String fullName)
    {
        this.fullName=fullName;
    }
    public String getFullName()
    {
        return fullName;
    }

    public void setEmail(String email)
    {
        this.email=email;
    }
    public String getEmail()
    {
        return email;
    }

    public void setPassword(String password)
    {
        this.password=password;
    }
    public String getPassword()
    {
        return password;
    }

    public void setRole(String role)
    {
        this.role=role;
    }
    public String getRole()
    {
        return role;
    }

    public void setPreferredLearningStyle(String preferredLearningStyle)
    {
        this.preferredLearningStyle=preferredLearningStyle;
    }
    public String getPreferredLearningStyle()
    {
        return preferredLearningStyle;
    }

    public void setCreatedAt(LocalDateTime createdAt)
    {
        this.createdAt=createdAt;
    }
    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }
}
