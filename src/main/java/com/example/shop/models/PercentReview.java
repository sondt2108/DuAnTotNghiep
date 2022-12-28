package com.example.shop.models;

public class PercentReview {
    
    private int scores;

    private double scoresPercent;

    public int getScores() {
        return scores;
    }

    public void setScore(int scores) {
        this.scores = scores;
    }

    public double getScorePercent() {
        return scoresPercent;
    }

    public void setScorePercent(double scoresPercent) {
        this.scoresPercent = scoresPercent;
    }

    public PercentReview() {
    }

    public PercentReview(int scores, double scoresPercent) {
        this.scores = scores;
        this.scoresPercent = scoresPercent;
    }


    
}
