package com.bilmatch.bilfoot.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bilmatch.bilfoot.R;
import com.bilmatch.bilfoot.controllers.AnnouncementController;
import com.bilmatch.bilfoot.models.Program;
import com.bilmatch.bilfoot.models.User;
import com.bilmatch.bilfoot.view.announcements.ListAnnouncementsActivity;
import com.bilmatch.bilfoot.view.announcements.NewAnnouncementActivity;
import com.bilmatch.bilfoot.view.main_fragments.Player;
import com.bilmatch.bilfoot.view.registration.RegistrationUserDefiningsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileScreenActivity extends AppCompatActivity {
    //final keys to transfer data from another activity
    public static final String EMAIL_KEY = "email";
    public static final String OWN_PROFILE_KEY = "own_profile";

    boolean isOwnProfile;
    BottomNavigationView appmenu;
    String email;
    TextView name, dominantfoot, position, emailviewer;
    ListView list;
    MyListAdapter myAdapter;
    ArrayList<String> skillsArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        name = findViewById(R.id.name);
        dominantfoot = findViewById(R.id.dominantfoot);
        position = findViewById(R.id.positions);
        emailviewer = findViewById(R.id.email);
        list = findViewById(R.id.list);
        appmenu = findViewById(R.id.appmenu);

        getExtraInfo();


        //buraya özel yeteneklerin array listini koy
        skillsArr = new ArrayList<String>();
        myAdapter = new MyListAdapter(skillsArr, ProfileScreenActivity.this);


        if(isOwnProfile) {
            fillProfileData(Program.getInstance().user);
        }else {
            fetchOtherUserInfo();
        }




        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                ProfileScreenActivity.this,
                android.R.layout.simple_list_item_1,
                skillsArr
        );
        list.setAdapter(myAdapter);




        if(isOwnProfile) {
            appmenu.setOnItemSelectedListener(item -> {
                switch (item.getItemId()) {
                    //daha çok "case" ekleyerek diğer aktivitelere gitmesi de sağlanabilir,
                    //henüz yapılmadığı için sadece 1 tane var
                    //ekleme yapıldığında apps_menu ya item eklemeyi unutma
                    case R.id.newAnnouncement:
                        startActivity(new Intent(ProfileScreenActivity.this, NewAnnouncementActivity.class));
                        break;
                    case R.id.listAnnouncements:
                        startActivity(new Intent(ProfileScreenActivity.this, ListAnnouncementsActivity.class));
                }
                return true;
            });
        }else {
            appmenu.setVisibility(View.GONE);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }


    }

    @Override
    public boolean onNavigateUp() {


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            startActivity(new Intent(ProfileScreenActivity.this,ListAnnouncementsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Fills the profile screen based on this user model
     * @param user the user model that we want to fill with
     */
    private void fillProfileData(User user) {


        emailviewer.setText("E-Mail Adress: " + user.getEmail());
        name.setText(user.getUsername());
        String dominantFootText = "";
        if(user.getDominantFoot().size() == 1) {
            dominantFootText = user.getDominantFoot().get(0);
        }else {
            dominantFootText = user.getDominantFoot().get(0) + " and " + user.getDominantFoot().get(1);
        }
        dominantfoot.setText("Dominant Foot: " + dominantFootText);

        String preferredPositionsText = "";
        for (String position:user.getPreferredPositions()) {
            preferredPositionsText += position + ", ";
        }

        position.setText("Preferred Position(s): " + preferredPositionsText);

        skillsArr.clear();

        for (String skill:user.getSpecialSkills()) {
            skillsArr.add(skill);
        }

        list.setAdapter(myAdapter);

    }

    /**
     * Takes the data from other activity
     *
     * Decides on whether the user want to see other people's profile
     * or his/her own profile
     */
    private void getExtraInfo() {
        Bundle extras = getIntent().getExtras();
        Log.d("EXTRA","EXTRA");
        if (extras != null) {
            isOwnProfile = extras.getBoolean(OWN_PROFILE_KEY,true);
            email = extras.getString(EMAIL_KEY, Program.getInstance().user.getEmail());
        }else  {
            isOwnProfile = true;
            email = Program.getInstance().user.getEmail();
        }
    }

    /**
     * Fetch the user model from firebase
     *
     * If we want to see the other people's profile, this method
     * finds the user info from database
     */
    private void fetchOtherUserInfo() {
        Log.d("FETCH","FETCH");

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference = db.getReference(User.class.getSimpleName());

        reference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = null;
                for (DataSnapshot childSnapshot: snapshot.getChildren()) {
                    user = childSnapshot.getValue(User.class);
                }


                Log.d("SNAPOSH",user + " s");


                if(user != null) {
                    Log.d("USER",user.toString());
                    fillProfileData(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("USER","cancelled");
            }
        });

    }

    private class MyListAdapter extends BaseAdapter implements ListAdapter {
        private ArrayList<String> list = new ArrayList<String>();
        private Context context;


        public MyListAdapter(ArrayList<String> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int pos) {
            return list.get(pos);
        }

        @Override
        public long getItemId(int pos) {
            return -1;

        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_user_skills, null);
            }

            //CHANGE FOR EVERY DIFFERENT FRAGMENT
            TextView listItemText = (TextView) view.findViewById(R.id.skill);
            listItemText.setText(list.get(position));


            return view;


        }
    }
}