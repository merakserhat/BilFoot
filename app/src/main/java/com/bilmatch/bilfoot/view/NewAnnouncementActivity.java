package com.bilmatch.bilfoot.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bilmatch.bilfoot.R;

public class NewAnnouncementActivity extends AppCompatActivity {
    Button findPlayerBtn;
    Button findTeamBtn;
    Button findOpponentBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_announcement);
        findPlayerBtn=findViewById(R.id.fPlayerBtn);
        findTeamBtn=findViewById(R.id.fTeamBtn);
        findOpponentBtn=findViewById(R.id.fOpponentBtn);


        //bir pozisyonda oyuncu ilanı
        findPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //takım arama ilanı
        findTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

            }
        });

        //rakip ilanı
        findOpponentBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

            }
        });



    }


}