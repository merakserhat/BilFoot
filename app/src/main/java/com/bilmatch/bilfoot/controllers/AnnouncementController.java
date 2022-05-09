package com.bilmatch.bilfoot.controllers;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bilmatch.bilfoot.models.announcement.Announcement;
import com.bilmatch.bilfoot.models.announcement.OpponentAnnouncement;
import com.bilmatch.bilfoot.models.announcement.PlayerAnnouncement;
import com.bilmatch.bilfoot.models.announcement.TeamAnnouncement;
import com.bilmatch.bilfoot.view.ProfileScreenActivity;
import com.bilmatch.bilfoot.view.main_fragments.Player;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AnnouncementController {

    public static Task<Void> addAnnouncement(Announcement announcement, String announcementName) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference = db.getReference(announcementName);

        return reference.push().setValue(announcement);
    }

    public static void subscribeAnnouncementStream(NewAnnouncementNotifier newAnnouncementNotifier, Class<? extends Announcement> valueType) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference = db.getReference(valueType.getSimpleName());

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Announcement announcement = snapshot.getValue(valueType);
                newAnnouncementNotifier.newAnnouncementArrived(announcement);
                /*
                for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    newAnnouncementNotifier.newAnnouncementArrived(dataSnapshot.getValue(valueType));
                }*/
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void announcementShowProfileButtonClicked(Activity activity, View v) {

        RelativeLayout parentLayout = (RelativeLayout) v.getParent();
        TextView textView = (TextView) parentLayout.getChildAt(0);

        String email = textView.getText().toString().split(" ")[0] + "@ug.bilkent.edu.tr";


        Intent i = new Intent(activity, ProfileScreenActivity.class);
        i.putExtra(ProfileScreenActivity.EMAIL_KEY,email);
        i.putExtra(ProfileScreenActivity.OWN_PROFILE_KEY,false);
        activity.startActivity(i);
    }
}

/*



    PlayerAnnouncement playerAnnouncement = new PlayerAnnouncement();

                playerAnnouncement.setAnnouncerEmail(Program.getInstance().user.getEmail());
                        ArrayList<String> positions = new ArrayList<>();
        positions.add("Ati242");
        positions.add("FM");
        playerAnnouncement.setPositions(positions);


        AnnouncementController.addAnnouncement(playerAnnouncement,PlayerAnnouncement.class.getSimpleName()).addOnSuccessListener(suc -> {
        Log.d("SUCCES","sa");
        }).addOnFailureListener(err -> {
        Log.e("ERROR", err.getMessage());
        });;

 */