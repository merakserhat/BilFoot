package com.bilmatch.bilfoot.models.announcement;

import android.media.Image;

import com.bilmatch.bilfoot.models.User;

enum AnnouncementType {
    TEAM,PLAYER,OPPONENT
}

public abstract class Announcement {
    private User announcer;
    private AnnouncementType announcementType;
    private int id;

    public abstract String getMessage();

    public abstract Image getImage();

}
