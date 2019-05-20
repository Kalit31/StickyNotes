package com.example.stickynotes.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.stickynotes.R;
import com.example.stickynotes.adapters.ListAdapter;
import com.example.stickynotes.model.Item;

import java.util.ArrayList;


public class View_Note extends Fragment {

    ArrayList<Item> items;


    public View_Note() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_view__note, container, false);
        items = new ArrayList<>();

        for(int i=1;i<=10;i++)
        {
            Item ob = new Item(Integer.toString(i));
            items.add(ob);
        }
        ListAdapter adapter = new ListAdapter(items, getActivity());
        ListView listView = v.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        return v;
    }
}