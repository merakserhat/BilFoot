package com.bilmatch.bilfoot.models;

public class User {
    private int id;
    private String email;
    private String name;
    private String dominantFoot;
    private Position[] preferredPositions;
    private Skill[] specialSkills;

    public void setSpecialSkills(Skill[] specialSkills) {
        this.specialSkills = specialSkills;
    }

    public void setName(String name) {
        this.name = name;
    }
}
