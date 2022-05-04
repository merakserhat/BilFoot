package com.bilmatch.bilfoot.models;

public class User {
    private int id;
    private String email;
    private String username;
    private String[] dominantFoot;
    private Position[] preferredPositions;
    private Skill[] specialSkills;

    public void setSpecialSkills(Skill[] specialSkills) {
        this.specialSkills = specialSkills;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDominantFoot(String[] dominantFoot) {
        this.dominantFoot = dominantFoot;
    }

    public void setPreferredPositions(Position[] preferredPositions) {
        this.preferredPositions = preferredPositions;
    }
}
