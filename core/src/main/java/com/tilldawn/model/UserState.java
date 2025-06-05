package com.tilldawn.model;

public class UserState {
    private int score = 0;
    private int kills = 0;
    private int survivalTime = 0;

    public int getScore() {
        return score;
    }

    public int getKills() {
        return kills;
    }

    public int getSurvivalTime() {
        return survivalTime;
    }

    public void advanceKills(int kills) {
        this.kills += kills;
    }

    public void advanceSurvivalTime(int survivalTime) {
        this.survivalTime += survivalTime;
    }

    public void advanceScore(int score) {
        this.score += score;
    }
}
