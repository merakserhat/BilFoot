package com.bilmatch.bilfoot.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;

import com.bilmatch.bilfoot.Opponent;
import com.bilmatch.bilfoot.Player;
import com.bilmatch.bilfoot.R;
import com.bilmatch.bilfoot.Team;
import com.bilmatch.bilfoot.databinding.ActivityListAnnouncementsBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class ListAnnouncementsActivity extends AppCompatActivity {
    ActivityListAnnouncementsBinding binding;
    BottomNavigationView appmenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_announcements);
        binding = ActivityListAnnouncementsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new Player());
        appmenu = findViewById(R.id.appmenu);

        binding.bNvgView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){

                case R.id.player:
                    replaceFragment(new Player());
                    break;
            case R.id.team:
                replaceFragment(new Team());
                break;
            case R.id.opponent:
                replaceFragment(new Opponent());
                break;}

            return true;
        });

        appmenu.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                //daha çok "case" ekleyerek diğer aktivitelere gitmesi de sağlanabilir,
                //henüz yapılmadığı için sadece 1 tane var
                //ekleme yapıldığında apps_menu ya item eklemeyi unutma
                case R.id.newAnnouncement:
                    startActivity(new Intent(ListAnnouncementsActivity.this, NewAnnouncementActivity.class));
                    break;
            }
            return true;
        });




    }

    private void replaceFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}