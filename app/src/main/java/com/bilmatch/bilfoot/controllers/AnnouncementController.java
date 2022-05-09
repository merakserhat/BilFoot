package com.bilmatch.bilfoot.controllers;

import com.bilmatch.bilfoot.models.announcement.Announcement;
import com.bilmatch.bilfoot.models.announcement.PlayerAnnouncement;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AnnouncementController {



    public static Task<Void> addNewPlayerAnnouncement(PlayerAnnouncement announcement) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference = db.getReference(PlayerAnnouncement.class.getSimpleName());

        return reference.push().setValue(announcement);
    }
}
