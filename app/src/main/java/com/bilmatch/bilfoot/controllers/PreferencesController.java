package com.bilmatch.bilfoot.controllers;

import android.content.Context;
import android.content.SharedPreferences;

import com.bilmatch.bilfoot.models.Program;
import com.bilmatch.bilfoot.models.User;

import java.util.ArrayList;
import java.util.Arrays;

public class PreferencesController {

    private static final String USER_ID_KEY = "user_id";
    private static final String USER_EMAIL_KEY = "user_email";
    private static final String USER_USERNAME_KEY = "user_username";
    private static final String USER_DOMINANT_FOOT_KEY = "user_dominant_foot";
    private static final String USER_PREFERRED_POSITIONS_KEY = "user_preferred_positions";
    private static final String USER_SPECIAL_SKILLS_KEY = "user_special_skills";


    public static User fetchUserFromPreferences(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);

        String userId = mPrefs.getString(USER_ID_KEY,null);
        String userUsername = mPrefs.getString(USER_USERNAME_KEY,null);
        String userEmail = mPrefs.getString(USER_EMAIL_KEY,null);
        String userDominantFootValue = mPrefs.getString(USER_DOMINANT_FOOT_KEY,null);
        String userPreferredPositionsValue = mPrefs.getString(USER_PREFERRED_POSITIONS_KEY,null);
        String userSpecialSkillsValue = mPrefs.getString(USER_SPECIAL_SKILLS_KEY,null);

        if(userEmail == null) {
            return null;
        }

        ArrayList<String> userDominantFoot = new ArrayList<>(Arrays.asList(userDominantFootValue.split("%")));

        ArrayList<String> userPreferredPositions = new ArrayList<>(Arrays.asList(userPreferredPositionsValue.split("%")));

        ArrayList<String> userSpecialSkills = new ArrayList<>(Arrays.asList(userSpecialSkillsValue.split("%")));

        User user = new User();
        user.setId(userId);
        user.setEmail(userEmail);
        user.setUsername(userUsername);
        user.setDominantFoot(userDominantFoot);
        user.setPreferredPositions(userPreferredPositions);
        user.setSpecialSkills(userSpecialSkills);

        return user;

    }

    public static void cleanUserFromPreferences(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = mPrefs.edit();

        edit.clear();
        edit.apply();
    }

    public static void saveUserToPreferences(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = mPrefs.edit();

        edit.putString(USER_ID_KEY, Program.getInstance().user.getId());
        edit.putString(USER_EMAIL_KEY, Program.getInstance().user.getEmail());
        edit.putString(USER_USERNAME_KEY, Program.getInstance().user.getUsername());

        //save arraylists by splitting with %
        StringBuilder dominantFootValue = new StringBuilder();
        for (String dominantFoot : Program.getInstance().user.getDominantFoot()) {
            dominantFootValue.append(dominantFoot).append("%");
        }
        dominantFootValue.deleteCharAt(dominantFootValue.length() - 1);
        edit.putString(USER_DOMINANT_FOOT_KEY, dominantFootValue.toString());

        StringBuilder preferredPositionsValue = new StringBuilder();
        for (String position : Program.getInstance().user.getPreferredPositions()) {
            preferredPositionsValue.append(position).append("%");
        }
        preferredPositionsValue.deleteCharAt(dominantFootValue.length() - 1);
        edit.putString(USER_PREFERRED_POSITIONS_KEY, preferredPositionsValue.toString());

        StringBuilder specialSkillsValue = new StringBuilder();
        for (String skill : Program.getInstance().user.getSpecialSkills()) {
            specialSkillsValue.append(skill).append("%");
        }
        specialSkillsValue.deleteCharAt(dominantFootValue.length() - 1);
        edit.putString(USER_SPECIAL_SKILLS_KEY, specialSkillsValue.toString());

        edit.apply();
    }


}
