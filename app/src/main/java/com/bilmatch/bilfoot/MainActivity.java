package com.bilmatch.bilfoot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bilmatch.bilfoot.controllers.AuthenticationController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private AuthenticationController authenticationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        //Burası daha önceden oturum açılmış mı kontrol eder
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        //if(currentUser != null){
          //  reload();
        //}

        authenticationController = new AuthenticationController(mAuth);

        //authenticationController.signIn(this,"merakserhat@gmail.com","123456");
        //authenticationController.register(this,"merakserhat@gmail.com","123456");

    }
}
