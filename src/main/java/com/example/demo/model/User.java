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
    
    public void getName(String fullName)
    {
        this.fullName=fullName;
    }
    public void getName(String fullName)
    {
        this.fullName=fullName;
    }
    public void getName(String fullName)
    {
        this.fullName=fullName;
    }
    public void getName(String fullName)
    {
        this.fullName=fullName;
    }
    public void getName(String fullName)
    {
        this.fullName=fullName;
    }
    public void getName(String fullName)
    {
        this.fullName=fullName;
    }

}
