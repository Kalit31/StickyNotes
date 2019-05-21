package com.example.stickynotes.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.stickynotes.DatabaseHelper;
import com.example.stickynotes.R;
import com.example.stickynotes.adapters.ListAdapter;
import com.example.stickynotes.model.Item;

import java.util.ArrayList;


public class View_Note extends Fragment {

   DatabaseHelper myDB;

    public View_Note()
    {
        // Required empty public constructor
    }

    public static View_Note newInstance() {
        View_Note fragment = new View_Note();
        return fragment;
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
        final ArrayList<Item> item =new ArrayList<Item>();
               ListView listView =v.findViewById(R.id.list_view);
        ListAdapter adapter=new ListAdapter(item,getActivity());
        myDB=new DatabaseHelper(getActivity());
        Cursor data = myDB.getListContents();
        if(data.getCount()==0)
        {
            Toast.makeText(getActivity(),"No Notes!!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(data.moveToNext())
            {
                item.add(new Item(data.getString(1)));
            }
        }
        item.add(new Item("Kalit"));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),item.get(position).getNote(),Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}