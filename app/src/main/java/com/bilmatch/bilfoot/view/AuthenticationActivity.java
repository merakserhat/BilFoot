package com.bilmatch.bilfoot.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bilmatch.bilfoot.R;
import com.bilmatch.bilfoot.controllers.AuthenticationController;
import com.bilmatch.bilfoot.models.Program;
import com.bilmatch.bilfoot.models.Skill;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthenticationActivity extends AppCompatActivity {

    //View variables
    TextView txtLoginInstead;
    TextView txtRegisterInstead;

    LinearLayout loginView;
    LinearLayout registerView;

    EditText loginPassword;
    EditText loginEmail;

    EditText registerPassword;
    EditText registerEmail;
    EditText registerUsername;

    Button btnLogin;
    Button btnRegister;

    CheckBox rememberMeCheckbox;

    //Service Variable
    private FirebaseAuth mAuth;
    private AuthenticationController authenticationController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        mAuth = FirebaseAuth.getInstance();
        authenticationController = new AuthenticationController(mAuth);

        initializeVariables();
        setButtonActions();
    }

    private void setButtonActions() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();
                boolean rememberMe = rememberMeCheckbox.isChecked();
                Program.getInstance().isRememberMe = rememberMe;

                if(email.isEmpty()) {
                    loginEmail.setError("E-mail address can not be empty");
                }

                if(password.isEmpty()) {
                    loginPassword.setError("Password can not be empty");
                }

                AuthenticationController.AuthenticationErrors formError = authenticationController.signIn(AuthenticationActivity.this,email,password,rememberMe);

                switch (formError){
                    case EMAIL: loginEmail.setError("This is not a valid Bilkent Mail");
                        break;
                    case PASSWORD: loginPassword.setError("Password must be at least 6 characters");
                        break;
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                String username = registerUsername.getText().toString();

                if(email.isEmpty()) {
                    registerEmail.setError("E-mail address can not be empty");
                }

                if(password.isEmpty()) {
                    registerPassword.setError("Password can not be empty");
                }

                if(username.isEmpty()) {
                    registerUsername.setError("Username address can not be empty");
                }

                AuthenticationController.AuthenticationErrors formError = authenticationController.register(AuthenticationActivity.this,email,password,username);

                switch (formError){
                    case EMAIL: registerEmail.setError("This is not a valid Bilkent Mail");
                    break;
                    case PASSWORD: registerPassword.setError("Password must be at least 6 characters");
                    break;
                    case USERNAME: registerUsername.setError("Username can only contain alphanumeric characters");
                    break;
                }
            }
        });
    }

    public   void  onRegisterInsteadClicked(View v) {
        loginView.setVisibility(View.GONE);
        registerView.setVisibility(View.VISIBLE);
    }

    public void  onLoginInsteadClicked(View v) {
        loginView.setVisibility(View.VISIBLE);
        registerView.setVisibility(View.GONE);
    }

    private void initializeVariables() {
        txtLoginInstead = findViewById(R.id.loginInstead);
        txtRegisterInstead = findViewById(R.id.registerInstead);

        loginView = findViewById(R.id.loginView);
        registerView = findViewById(R.id.registerView);

        loginPassword = findViewById(R.id.loginPassword);
        loginEmail = findViewById(R.id.loginEmail);

        registerPassword = findViewById(R.id.registerPassword);
        registerEmail = findViewById(R.id.registerEmail);
        registerUsername = findViewById(R.id.registerUsername);

        btnLogin = findViewById(R.id.loginButton);
        btnRegister = findViewById(R.id.registerButton);

        rememberMeCheckbox = findViewById(R.id.rememberMe);
    }
}