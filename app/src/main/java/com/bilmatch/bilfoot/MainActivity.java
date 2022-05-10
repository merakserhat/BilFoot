package com.bilmatch.bilfoot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bilmatch.bilfoot.controllers.AuthenticationController;
import com.bilmatch.bilfoot.controllers.PreferencesController;
import com.bilmatch.bilfoot.models.User;
import com.bilmatch.bilfoot.view.AuthenticationActivity;

import com.bilmatch.bilfoot.view.announcements.ListAnnouncementsActivity;
import com.bilmatch.bilfoot.view.announcements.NewAnnouncementActivity;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private AuthenticationController authenticationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();


        authenticationController = new AuthenticationController(mAuth);
        //startActivity(new Intent(this, ProfileScreenActivity.class));
        //startActivity(new Intent(this, AuthenticationActivity.class));
        User user = PreferencesController.fetchUserFromPreferences(this);
        if(user != null) {
            startActivity(new Intent(this, NewAnnouncementActivity.class));
        }else {
            startActivity(new Intent(this, AuthenticationActivity.class));
        }


    }
}
