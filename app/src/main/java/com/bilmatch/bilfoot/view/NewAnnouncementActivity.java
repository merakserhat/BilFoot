package com.bilmatch.bilfoot.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bilmatch.bilfoot.R;
import com.bilmatch.bilfoot.controllers.AnnouncementController;
import com.bilmatch.bilfoot.models.Program;
import com.bilmatch.bilfoot.models.announcement.OpponentAnnouncement;
import com.bilmatch.bilfoot.models.announcement.PlayerAnnouncement;
import com.bilmatch.bilfoot.models.announcement.TeamAnnouncement;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

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
        appmenu = findViewById(R.id.appmenu);


        //bir pozisyonda oyuncu ilanı
        findPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlayerAnnouncement playerAnnouncement = new PlayerAnnouncement();

                playerAnnouncement.setAnnouncerEmail(Program.getInstance().user.getEmail());
                ArrayList<String> positions = new ArrayList<>();
                positions.add("TP");
                playerAnnouncement.setPositions(positions);

                AnnouncementController.addAnnouncement(playerAnnouncement,PlayerAnnouncement.class.getSimpleName()).addOnSuccessListener(suc -> {
                    Log.d("SUCCES","sa");
                }).addOnFailureListener(err -> {
                    Log.e("ERROR", err.getMessage());
                });;
            }
        });

        //takım arama ilanı
        findTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                TeamAnnouncement teamAnnouncement = new TeamAnnouncement();
                AnnouncementController.addAnnouncement(teamAnnouncement,TeamAnnouncement.class.getSimpleName()).addOnSuccessListener(suc -> {
                    Log.d("SUCCES","sa");
                    Toast.makeText(NewAnnouncementActivity.this,"You have announced to find a team!",Toast.LENGTH_LONG).show();
                }).addOnFailureListener(err -> {
                    Log.e("ERROR", err.getMessage());
                    Toast.makeText(NewAnnouncementActivity.this,"Something went wrong while creating an announcement",Toast.LENGTH_LONG).show();
                });;
            }
        });

        //rakip ilanı
        findOpponentBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                OpponentAnnouncement opponentAnnouncement = new OpponentAnnouncement();
                AnnouncementController.addAnnouncement(opponentAnnouncement,TeamAnnouncement.class.getSimpleName()).addOnSuccessListener(suc -> {
                    Log.d("SUCCES","sa");
                    Toast.makeText(NewAnnouncementActivity.this,"You have announced to find an opponent!",Toast.LENGTH_LONG).show();
                }).addOnFailureListener(err -> {
                    Log.e("ERROR", err.getMessage());
                    Toast.makeText(NewAnnouncementActivity.this,"Something went wrong while creating an announcement",Toast.LENGTH_LONG).show();

                });
            }
        });

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



    }


}