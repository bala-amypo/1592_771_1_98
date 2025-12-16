package com.example.demo.model;

public class Course {
    
    private long id;
    private String title;
    private String description;
    private User user;
    private String category;
    private LocalDateTime createdAt;

    public Course(){}

    public Course(String title,String description,User user,String Category,LocalDateTime createdAt)
    {
        this.title=title;
        this.description=description;
        this.user=user;
        this.category=category;
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

    public void setTitle(String title)
    {
        this.title=title;
    }
    public String getTitle()
    {
        return title;
    }

    public void setDescription(String description)
    {
        this.description=description;
    }
    public String getDescription()
    {
        return description;
    }

    public void setId(int id)
    {
        this.id=id;
    }
    public long getId()
    {
        return id;
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
