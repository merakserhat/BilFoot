package com.bilmatch.bilfoot.models.announcement;

import android.media.Image;

import com.bilmatch.bilfoot.models.announcement.Announcement;

public class TeamAnnouncement extends Announcement {

    public TeamAnnouncement() {
        announcementType = TEAM;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public Image getImage() {
        return null;
    }
}
