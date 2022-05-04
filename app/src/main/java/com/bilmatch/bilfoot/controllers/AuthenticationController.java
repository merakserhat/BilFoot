package com.bilmatch.bilfoot.controllers;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bilmatch.bilfoot.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class AuthenticationController {
    private FirebaseAuth mAuth;

    public AuthenticationController(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    public enum AuthenticationErrors {
        PASSWORD,
        USERNAME,
        EMAIL,
        VALID
    }

    public AuthenticationErrors signIn(Activity activity, String email, String password, boolean rememberMe) {

        TextView txtLoginError = activity.findViewById(R.id.txtLoginError);
        txtLoginError.setVisibility(View.GONE);

        //check password
        if(!checkPassword(password)) return AuthenticationErrors.PASSWORD;
        //check email
        if(!checkEmailAddress(email)) return AuthenticationErrors.EMAIL;

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("AUTHENTICATION", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("USER",user.getUid());

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("AUTHENTICATION", "signInWithEmail:failure", task.getException());
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                            //        Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                            txtLoginError.setVisibility(View.VISIBLE);

                        }
                    }
                });

        return AuthenticationErrors.VALID;
    }

    public AuthenticationErrors register(Activity activity,String email, String password, String username) {
        TextView txtRegisterError = activity.findViewById(R.id.txtRegisterError);
        txtRegisterError.setVisibility(View.GONE);

        //check password
        if(!checkPassword(password)) return AuthenticationErrors.PASSWORD;
        //check email
        if(!checkEmailAddress(email)) return AuthenticationErrors.EMAIL;
        //check username
        if(!checkUsername(username)) return AuthenticationErrors.USERNAME;

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            //Fill registration defining controller singleton
                            RegistrationDefiningController.getInstance().email = email;
                            RegistrationDefiningController.getInstance().username = username;
                            RegistrationDefiningController.getInstance().id = user != null ? user.getUid() : null;

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("AUTHENTICATION", "createUserWithEmail:failure", task.getException());
                            txtRegisterError.setVisibility(View.VISIBLE);
                        }
                    }
                });

        return AuthenticationErrors.VALID;
    }

    private boolean checkEmailAddress(String email) {
        return email.endsWith("@ug.bilkent.edu.tr");
    }

    private  boolean checkPassword(String password) {
        return password.length() >= 6;
    }

    public boolean checkUsername(String username) {
        return  username != null && username.matches("^[a-zA-Z0-9]*$");
    }


}
