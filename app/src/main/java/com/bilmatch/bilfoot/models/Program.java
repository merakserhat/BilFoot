package com.bilmatch.bilfoot.models;

/**
 * This singleton class holds the data for the application
 * It allows us to reach these data from wherever we want
 */
public class Program {
    //#region Singleton set up
    private Program() {}

    private static Program instance = null;

    public static Program getInstance() {
        if(instance == null) {
            instance = new Program();
        }

        return instance;
    }
    //#endregion

    public User user;

    public boolean isRememberMe = false;


}
