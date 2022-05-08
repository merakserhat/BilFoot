package com.bilmatch.bilfoot.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bilmatch.bilfoot.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NewAnnouncementActivity extends AppCompatActivity {
    Button findPlayerBtn;
    Button findTeamBtn;
    Button findOpponentBtn;
    BottomNavigationView appmenu;


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
/*
        appmenu.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                //daha çok "case" ekleyerek diğer aktivitelere gitmesi de sağlanabilir,
                //henüz yapılmadığı için sadece 1 tane var
                //ekleme yapıldığında apps_menu'ya item eklemeyi ve gidilecek classı eklemeyi unutma
                case R.id.listAnnouncements:
                    startActivity(new Intent(NewAnnouncementActivity.this, ListAnnouncementsActivity.class));
                    break;
            }
            return true;
        });
*/


    }


}