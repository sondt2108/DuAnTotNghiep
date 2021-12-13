package com.example.datn.models;

public class PercentReview {
    
    private int score;

    private double scorepercent;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getScorePercent() {
        return scorepercent;
    }

    public void setScorePercent(double scorepercent) {
        this.scorepercent = scorepercent;
    }

    public PercentReview() {
    }

    public PercentReview(int score, double scorepercent) {
        this.score = score;
        this.scorepercent = scorepercent;
    }


    
}
