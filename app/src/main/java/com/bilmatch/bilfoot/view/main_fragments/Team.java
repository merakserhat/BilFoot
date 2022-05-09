package com.bilmatch.bilfoot.view.main_fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bilmatch.bilfoot.R;
import com.bilmatch.bilfoot.controllers.AnnouncementController;
import com.bilmatch.bilfoot.controllers.NewAnnouncementNotifier;
import com.bilmatch.bilfoot.models.announcement.Announcement;
import com.bilmatch.bilfoot.models.announcement.OpponentAnnouncement;
import com.bilmatch.bilfoot.models.announcement.PlayerAnnouncement;
import com.bilmatch.bilfoot.models.announcement.TeamAnnouncement;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Team} factory method to
 * create an instance of this fragment.
 */
public class Team extends Fragment implements NewAnnouncementNotifier {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    MyListAdapter myAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ArrayList<String> items;
    private ListView listView;

    public Team() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Team.
     */
    // TODO: Rename and change types and number of parameters
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, container, false);

        items = new ArrayList<String>();
        items.add("wjıoqdıqwoıjd");
        items.add("asdsadas");


        listView = (ListView) view.findViewById(R.id.teamList);

        //Log.e("")

        myAdapter = new MyListAdapter(items, this.getContext());
        listView.setAdapter(myAdapter);

        /*btn = (Button)view.findViewById(R.id.btn111);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //"1" yerine eklenecek mesajı yaz. buton yerine yeni method yaz
                items.add("1");
                listView.setAdapter(stringArrayAdapter);
            }
        });*/

        AnnouncementController.subscribeAnnouncementStream(this, TeamAnnouncement.class);


        return view;
    }

    @Override
    public void newAnnouncementArrived(Announcement announcement) {
        Log.d("sa","asfşlamsf");
        if(announcement instanceof TeamAnnouncement) {
            StringBuilder announcementMessage = new StringBuilder();
            announcementMessage.append(announcement.getAnnouncerEmail().split("@")[0]);
            announcementMessage.append(" is looking for a team!");

            items.add(0,announcementMessage.toString());
            listView.setAdapter(myAdapter);
        }
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
                view = inflater.inflate(R.layout.item_ann_team, null);
            }

            //CHANGE FOR EVERY DIFFERENT FRAGMENT
            TextView listItemText = (TextView) view.findViewById(R.id.annMessage);
            listItemText.setText(list.get(position));
            //ICON NAMES WILL BE DIFFERENT
            ImageView mYicon = (ImageView) view.findViewById(R.id.playerIcon);

            //Handle buttons and add onClickListeners
            ImageButton profileBtn = (ImageButton) view.findViewById(R.id.profileBtn);

            profileBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: announcer'ın profiline götür
                    AnnouncementController.announcementShowProfileButtonClicked(Team.this.getActivity(),v);
                    notifyDataSetChanged();
                }
            });
            return view;
        }
    }
}