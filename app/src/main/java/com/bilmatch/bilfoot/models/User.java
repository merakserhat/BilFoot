package com.bilmatch.bilfoot.models;

import java.util.List;

public class User {
    private String id;
    private String email;
    private String username;
    private List<String> dominantFoot;
    private List<String> preferredPositions;
    private List<String> specialSkills;

    public void setSpecialSkills(List<String> specialSkills) {
        this.specialSkills = specialSkills;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDominantFoot(List<String> dominantFoot) {
        this.dominantFoot = dominantFoot;
    }

    public void setPreferredPositions(List<String> preferredPositions) {
        this.preferredPositions = preferredPositions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getDominantFoot() {
        return dominantFoot;
    }

    public List<String> getPreferredPositions() {
        return preferredPositions;
    }

    public List<String> getSpecialSkills() {
        return specialSkills;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", dominantFoot=" + dominantFoot +
                ", preferredPositions=" + preferredPositions +
                ", specialSkills=" + specialSkills +
                '}';
    }
}
