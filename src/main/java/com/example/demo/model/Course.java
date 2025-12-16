package com.example.demo.model;

public class Course {
    
    private long id;
    private String title;
    private String description;
    private User user;
    private String Category;
    private LocalDateTime createdAt;

    public Course(){}

    public Course(String title,String description,User user,)






    public void setCreatedAt(LocalDateTime createdAt)
    {
        this.createdAt=createdAt;
    }
    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

}
