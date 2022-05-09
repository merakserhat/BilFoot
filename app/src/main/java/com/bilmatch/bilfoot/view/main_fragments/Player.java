package com.bilmatch.bilfoot.view.main_fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;

import android.widget.ListView;
import android.widget.TextView;

import com.bilmatch.bilfoot.R;
import com.bilmatch.bilfoot.controllers.AnnouncementController;
import com.bilmatch.bilfoot.controllers.NewAnnouncementNotifier;
import com.bilmatch.bilfoot.models.announcement.Announcement;
import com.bilmatch.bilfoot.models.announcement.PlayerAnnouncement;

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
    MyListAdapter myAdapter;
    //ArrayAdapter<String> stringArrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player, container, false);

        items = new ArrayList<String>();
        items.add("a");
        items.add("asdsadas");


        listView = (ListView) view.findViewById(R.id.playerList);



        myAdapter = new MyListAdapter(items, this.getContext());
        listView.setAdapter(myAdapter);
        //listView.setAdapter(new myListAdapter(this.getContext(), R.layout.item_ann_player, items));


        AnnouncementController.subscribeAnnouncementStream(this,PlayerAnnouncement.class);

        btn = (Button)view.findViewById(R.id.btn11);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO

            }
        });

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //make new buttons appear
            }
        });*/


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
            listView.setAdapter(myAdapter);
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
                view = inflater.inflate(R.layout.item_ann_player, null);
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
                    notifyDataSetChanged();
                }
            });
            return view;


        }
    }
}