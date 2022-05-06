package com.bilmatch.bilfoot;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Opponent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Opponent extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Opponent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment Opponent.
     */



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opponent, container, false);

        ArrayList<String> items = new ArrayList<String>();
        items.add("opponent");
        items.add("asdsadas");



        ListView listView = (ListView) view.findViewById(R.id.opponentList);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                items
        );
        listView.setAdapter(stringArrayAdapter);

        /*btn = (Button)view.findViewById(R.id.btn111);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //"1" yerine eklenecek mesajı yaz. buton yerine yeni method yaz
                items.add("1");
                listView.setAdapter(stringArrayAdapter);
            }
        });*/





        return view;
    }
}