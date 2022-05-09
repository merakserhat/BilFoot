package com.bilmatch.bilfoot.models.announcement;

import android.media.Image;

import com.bilmatch.bilfoot.models.User;



public abstract class Announcement {

    public static final String PLAYER = "PLAYER";
    public static final String OPPONENT = "OPPONENT";
    public static final String TEAM = "TEAM";

    protected String announcerEmail;
    protected String announcementType;

    public abstract String getMessage();

    public abstract Image getImage();

    public void setAnnouncerEmail(String announcer_email) {
        this.announcerEmail = announcer_email;
    }


    public String getAnnouncerEmail() {
        return announcerEmail;
    }

    public String getAnnouncementType() {
        return announcementType;
    }
}
