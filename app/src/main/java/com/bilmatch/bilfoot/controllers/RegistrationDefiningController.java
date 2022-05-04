package com.bilmatch.bilfoot.controllers;

import java.util.ArrayList;

public class RegistrationDefiningController {
    //#region Singleton set up
    private static RegistrationDefiningController instance = null;
    private RegistrationDefiningController() {
        dominantFeet = new ArrayList<>();
        userDefinings = new ArrayList<>();
        preferredPositions = new ArrayList<>();
    }

    public static RegistrationDefiningController getInstance() {
        if(instance == null) {
            instance = new RegistrationDefiningController();
        }
        return instance;
    }
    //#endregion

    public ArrayList<String> dominantFeet;
    public ArrayList<String> userDefinings;
    public ArrayList<String> preferredPositions;

    public String email;
    public String username;
    public String id;

}
