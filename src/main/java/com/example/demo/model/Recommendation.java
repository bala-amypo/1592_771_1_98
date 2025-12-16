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
        return user;
    }

    public void setGeneratedAt(LocalDateTime generatedAt)
    {
        this.generatedAt=generatedAt;

    }
    public LocalDateTime getGeneratedAt()
    {
        return generatedAt;
    }

    public void setRecommendedLessonIds(String recommendedLessonIds)
    {
        this.recommendedLessonIds=recommendedLessonIds;

    }
    public String getRecommendedLessonIds()
    {
        return recommendedLessonIds;
    }

    public void setBasisSnapshot(String basisSnapshot)
    {
        this.basisSnapshot=basisSnapshot;

    }
    public String getBasisSnapshot()
    {
        return basisSnapshot;
    }

    public void setConfidenceScore(Double confidenceScore)
    {
        this.confidenceScore=confidenceScore;
    }
    public Double getConfidenceScore()
    {
        return confidenceScore;
    }
}
