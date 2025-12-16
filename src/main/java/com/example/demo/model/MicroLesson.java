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
        return
    }

    public void setCourse(Course course)
    {
        
    }
    public Course getCourse()

    public void setTitle(String title)
    public String getTitle()

    public void setDurationMinutes(Integer durationMinutes)
    public Integer getDurationMinutes()

    public void setContentType(String contentType)
    public String getContentType()

    public void setDifficulty(String difficulty)
    public String getDifficulty()

    public void setTags(String tags)
    public String getTags()

    public void setPublishDate(LocalDate publishDate)
    public LocalDate getPublishDate()
}
