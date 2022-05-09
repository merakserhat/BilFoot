package com.bilmatch.bilfoot.view.main_fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bilmatch.bilfoot.R;
import com.bilmatch.bilfoot.controllers.AnnouncementController;
import com.bilmatch.bilfoot.controllers.NewAnnouncementNotifier;
import com.bilmatch.bilfoot.models.Program;
import com.bilmatch.bilfoot.models.announcement.Announcement;
import com.bilmatch.bilfoot.models.announcement.PlayerAnnouncement;
import com.bilmatch.bilfoot.view.registration.PositionSelectionActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Player#} factory method to
 * create an instance of this fragment.
 */
public class Player extends Fragment implements NewAnnouncementNotifier {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //List Variables
    ListView listView;
    Button btn;




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Player() {
        // Required empty public constructor
    }

    private ArrayList<String> items;
    ArrayAdapter<String> stringArrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player, container, false);

        items = new ArrayList<String>();
        items.add("a");
        items.add("asdsadas");


        listView = (ListView) view.findViewById(R.id.playerList);

        stringArrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                items
        );
        listView.setAdapter(stringArrayAdapter);
        //listView.setAdapter(new myListAdapter(this.getContext(), R.layout.item_ann, items));


        AnnouncementController.subscribeAnnouncementStream(this,PlayerAnnouncement.class);

        btn = (Button)view.findViewById(R.id.btn11);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //"1" yerine eklenecek mesajı yaz. buton yerine yeni method yaz
                /*items.add("1");
                listView.setAdapter(stringArrayAdapter);*/



                PlayerAnnouncement playerAnnouncement = new PlayerAnnouncement();

                playerAnnouncement.setAnnouncerEmail(Program.getInstance().user.getEmail());
                ArrayList<String> positions = new ArrayList<>();
                positions.add("Ati242");
                positions.add("FM");
                playerAnnouncement.setPositions(positions);


                AnnouncementController.addAnnouncement(playerAnnouncement,PlayerAnnouncement.class.getSimpleName()).addOnSuccessListener(suc -> {
                    Log.d("SUCCES","sa");
                }).addOnFailureListener(err -> {
                    Log.e("ERROR", err.getMessage());
                });;


            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //make new buttons appear
            }
        });





        return view;
    }


    @Override
    public void newAnnouncementArrived(Announcement announcement) {
        if(announcement instanceof PlayerAnnouncement) {
            Log.d("ANNOUNCEMENT","BURAA");
            StringBuilder announcementMessage = new StringBuilder();
            announcementMessage.append(announcement.getAnnouncerEmail().split("@")[0]);
            announcementMessage.append(" is looking for ");
            for (String position : ((PlayerAnnouncement) announcement).getPositions()) {
                announcementMessage.append(position).append(" ");
            }
            //Remove last space and replace with .
            announcementMessage.deleteCharAt(announcementMessage.length() - 1);
            announcementMessage.append(".");
            items.add(announcementMessage.toString());
            listView.setAdapter(stringArrayAdapter);
        }
    }

    /*private class myListAdapter extends ArrayAdapter<String>{
        private int layout;
        public myListAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
            super(context, resource, objects);
            layout = resource;
        }


        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder mainViewHolder = null;

            if(convertView == null){
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());
                convertView = layoutInflater.inflate(layout, parent);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.message = (TextView) convertView.findViewById(R.id.annMessage);
                viewHolder.btn = (Button) convertView.findViewById(R.id.profileBtn);
                viewHolder.btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                convertView.setTag(viewHolder);
            }

            else{
                mainViewHolder = (ViewHolder) convertView.getTag();
                mainViewHolder.message.setText(getItem(position));
            }
            return convertView;
        }
    }

    public class ViewHolder{
        TextView message;
        Button btn;

    }*/






}