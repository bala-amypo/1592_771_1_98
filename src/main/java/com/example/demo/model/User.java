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
    
    public User(long id,String fullName,String email,String password,String role,String preferredLearningStyle,LocalDateTime createdAt)
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
    public long getFullName()
    {
        return fullName;
    }

    public void setEmail(String email)
    {
        this.email=email;
    }
    public long getEmail()
    {
        return email;
    }

    public void setPassword(String password)
    {
        this.password=password;
    }
    public long getPassword()
    {
        return password;
    }

    public void setRole(String role)
    {
        this.role=role;
    }
    public long getId()
    {
        return id;
    }

    public void setPreferredLearningStyle(String preferredLearningStyle)
    {
        this.preferredLearningStyle=preferredLearningStyle;
    }
    public long getId()
    {
        return id;
    }

    public void setCreatedAt(LocalDateTime createdAt)
    {
        this.createdAt=createdAt;
    }
    public long getId()
    {
        return id;
    }
}
