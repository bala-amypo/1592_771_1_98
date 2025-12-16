package com.example.demo.model;

public class Recommendation {
    private long id;
    private User user;
    private LocalDateTime generatedAt;
    private String recommendedLessonIds;
    private String basisSnapshot;
    private Double confidenceScore;

    public Recommendation(){}

    public Recommendation(User user,LocalDateTime generatedAt,String recommendedLessonIds,String basisSnapshot,Double confidenceScore)
    {
        this.user=user;
        this.generatedAt=generatedAt;
        this.recommendedLessonIds=recommendedLessonIds;
        this.basisSnapshot=basisSnapshot;
        this.confidenceScore=confidenceScore;
    }

    public void setId()
    {
        this.id=id;
    }
    public long getId()

    public void setUser()
    {
        
    }
    public User getUser();

    public void setGeneratedAt();
    public LocalDateTime getGeneratedAt()

    public void setRecommendedLessonIds()
    public String getRecommendedLessonIds()

    public void setBasisSnapshot()
    public String getBasisSnapshot()

    public void setConfidenceScore()
    public Double getConfidenceScore()
}
