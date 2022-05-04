package com.bilmatch.bilfoot.view.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bilmatch.bilfoot.R;
import com.bilmatch.bilfoot.controllers.RegistrationDefiningController;

import java.util.ArrayList;

public class RegistrationUserDefiningsActivity extends AppCompatActivity {

    //Registration controller singleton
    RegistrationDefiningController registrationController;

    //defining layouts
    RelativeLayout speedyLayout;
    RelativeLayout deadlyFinisherLayout;
    RelativeLayout playmakerLayout;
    RelativeLayout tirelessLayout;
    RelativeLayout longDistanceLayout;
    RelativeLayout strongLayout;
    RelativeLayout headshotLayout;
    RelativeLayout creativeLayout;

    //dominant foot layouts
    RelativeLayout leftLayout;
    RelativeLayout rightLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_user_definings);

        registrationController = RegistrationDefiningController.getInstance();

        initializeVariables();
        giveLayoutListeners();

    }

    private void giveLayoutListeners() {
        View.OnClickListener definingClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String definingValue = ((TextView) view.findViewWithTag("text")).getText().toString();

                if(registrationController.userDefinings.contains(definingValue)) {
                    registrationController.userDefinings.remove(definingValue);
                    deactivateDefiningLayout((RelativeLayout) view);
                }else {
                    registrationController.userDefinings.add(definingValue);
                    activateDefiningLayout((RelativeLayout) view);
                }

            }
        };

        speedyLayout.setOnClickListener(definingClickListener);
        deadlyFinisherLayout.setOnClickListener(definingClickListener);
        playmakerLayout.setOnClickListener(definingClickListener);
        tirelessLayout.setOnClickListener(definingClickListener);
        longDistanceLayout.setOnClickListener(definingClickListener);
        strongLayout.setOnClickListener(definingClickListener);
        headshotLayout.setOnClickListener(definingClickListener);
        creativeLayout.setOnClickListener(definingClickListener);

        View.OnClickListener dominantFootClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dominantFoot = ((TextView) view.findViewWithTag("text")).getText().toString();

                if(registrationController.dominantFeet.contains(dominantFoot)) {
                    registrationController.dominantFeet.remove(dominantFoot);
                    deactivateDefiningLayout((RelativeLayout) view);
                }else {
                    registrationController.dominantFeet.add(dominantFoot);
                    activateDefiningLayout((RelativeLayout) view);
                }

            }
        };

        rightLayout.setOnClickListener(dominantFootClickListener);
        leftLayout.setOnClickListener(dominantFootClickListener);

    }

    private void initializeVariables() {
        speedyLayout = findViewById(R.id.speedyLayout);
        deadlyFinisherLayout = findViewById(R.id.deadlyFinisherLayout);
        playmakerLayout = findViewById(R.id.playmakerLayout);
        tirelessLayout = findViewById(R.id.tirelessLayout);
        longDistanceLayout = findViewById(R.id.longDistnaceLayout);
        strongLayout = findViewById(R.id.strongLayout);
        headshotLayout = findViewById(R.id.headshotLayout);
        creativeLayout = findViewById(R.id.creativeLayout);

        leftLayout = findViewById(R.id.leftFootContainer);
        rightLayout = findViewById(R.id.rightFootContainer);

    }

    private void deactivateDefiningLayout(RelativeLayout view) {
        View cardBackground = view.findViewWithTag("cardBackground");
        cardBackground.setBackgroundResource(R.drawable.rounded_edittext);

        TextView textView = view.findViewWithTag("text");
        textView.setTextColor(getResources().getColor(R.color.white));
    }

    private void activateDefiningLayout(RelativeLayout view) {
        View cardBackground = view.findViewWithTag("cardBackground");
        cardBackground.setBackgroundResource(R.drawable.rounded_edittext_selected);

        TextView textView = view.findViewWithTag("text");
        textView.setTextColor(getResources().getColor(com.google.android.material.R.color.secondary_text_default_material_light));
    }
}