package com.example.demo.model;

public class MicroLesson {
    
    private long id;
    private Course course;
    private String title;
    private Integer durationMinutes;
    private String contentType;
    private String difficulty;
    private String tags;
    private LocalDate publishDate;

    public MicroLesson(){}

    public MicroLesson(Course course,String title,Integer durationMinutes,String contentType,String difficulty,String tags,LocalDate publishDate)
    {
        this.course=course;
        this.title=title;
        this.durationMinutes=durationMinutes;
        this.contentType=contentType;
        this.difficulty=difficulty;
        this.tags=tags;
        this.publishDate=publishDate;
    }

    public void setId(long id)
    {
        this.course=course;
    }
    public long getId()
    {
        return id;
    }

    public void setCourse(Course course)
    {
        this.course=course;
    }
    public Course getCourse()
    {
        return course;
    }

    public void setTitle(String title)
    {
        this.title=title;

    }
    public String getTitle()
    {
        return title;
    }

    public void setDurationMinutes(Integer durationMinutes)
    {
        this.durationMinutes=durationMinutes;

    }
    public Integer getDurationMinutes()
    {
        return durationMinutes;
    }

    public void setContentType(String contentType)
    {
        this.contentType=contentType;

    }
    public String getContentType()
    {
        return contentType;
    }

    public void setDifficulty(String difficulty)
    {
        this.difficulty=difficulty;

    }
    public String getDifficulty()
    {
        return difficulty;
    }

    public void setTags(String tags)
    {
        this.tags=tags;

    }
    public String getTags()
    {
        return tags;
    }

    public void setPublishDate(LocalDate publishDate)
    {
        this.publishDate=publishDate;
    }
    public LocalDate getPublishDate()
    {
        return publishDate;
    }
}