package com.example.demo.model;

public class Progress {
    private long id;
    private User user;
    private MicroLesson microLesson;
    private String status;
    private Integer progressPercent;
    private LocalDateTime lastAccessedAt;
    private Double score;
    
    public Progress(){}

    public MicroLesson(User user,MicroLesson microLesson,String status,Integer progressPercent,LocalDateTime lastAccessedAt,Double score)
    {
        this.user=user;
        this.microLesson=microLesson;
        this.status=status;
        this.progressPercent=progressPercent;
        this.lastAccessedAt=lastAccessedAt;
        this.score=score;
    }

    public void setId(long id)
    {
        this.id=id;
    }
    public long getId()
    {
        return id;
    }

    public void setUser(User user)
    {
        this.user=user;
    }
    public User getUser()
    {
        return id;
    }

    public void setMicroLesson(MicroLesson microLesson)
    {
        this.microLesson=microLesson;
    }
    public MicroLesson getMicroLesson()
    {
        return microLesson;
    }

    public
    public

    public
    public

    public
    public

    public
    public

    public
    public
}
