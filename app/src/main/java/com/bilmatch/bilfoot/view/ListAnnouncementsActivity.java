package com.bilmatch.bilfoot.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.AdapterView;

import com.bilmatch.bilfoot.Opponent;
import com.bilmatch.bilfoot.Player;
import com.bilmatch.bilfoot.R;
import com.bilmatch.bilfoot.Team;
import com.bilmatch.bilfoot.databinding.ActivityListAnnouncementsBinding;

public class ListAnnouncementsActivity extends AppCompatActivity {
    ActivityListAnnouncementsBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_announcements);
        binding = ActivityListAnnouncementsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new Opponent());

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
    }

    private void replaceFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}