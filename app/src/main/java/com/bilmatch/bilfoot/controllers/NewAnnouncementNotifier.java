package com.bilmatch.bilfoot.controllers;

import com.bilmatch.bilfoot.models.announcement.Announcement;

public interface NewAnnouncementNotifier {
    void newAnnouncementArrived(Announcement announcement);
}
