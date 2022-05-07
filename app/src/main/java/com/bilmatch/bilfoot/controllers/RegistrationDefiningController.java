package com.bilmatch.bilfoot.controllers;

import com.bilmatch.bilfoot.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegistrationDefiningController {
    //#region Singleton set up
    private static RegistrationDefiningController instance = null;
    private RegistrationDefiningController() {
        dominantFeet = new ArrayList<>();
        userDefinings = new ArrayList<>();
        preferredPositions = new ArrayList<>();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(User.class.getSimpleName());
    }

    public static RegistrationDefiningController getInstance() {
        if(instance == null) {
            instance = new RegistrationDefiningController();
        }
        return instance;
    }
    //#endregion

    private DatabaseReference databaseReference;

    public ArrayList<String> dominantFeet;
    public ArrayList<String> userDefinings;
    public ArrayList<String> preferredPositions;

    public String email;
    public String username;
    public String id;

    public Task<Void> saveUser() {
        User user = new User();
        user.setDominantFoot(dominantFeet);
        user.setSpecialSkills(userDefinings);
        user.setPreferredPositions(preferredPositions);
        user.setEmail(email);
        user.setUsername(username);
        user.setId(id);

        return databaseReference.push().setValue(user);
    }


}
