package com.bilmatch.bilfoot.models.announcement;

import android.media.Image;

public class OpponentAnnouncement extends Announcement{

    public OpponentAnnouncement() {
        announcementType = OPPONENT;
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
