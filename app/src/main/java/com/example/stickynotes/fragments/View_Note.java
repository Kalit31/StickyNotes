package com.example.stickynotes.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder a_builder =new AlertDialog.Builder(getActivity());
                a_builder.setMessage(item.get(position).getNote()).setCancelable(false).setPositiveButton("DELETE", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            Integer deletedRows=myDB.deleteData(item.get(position).getNote());
                            if(deletedRows > 0) {

                            }
                            else
                                Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("CLOSE", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog=a_builder.create();
                alertDialog.setTitle("Notes");
                alertDialog.show();
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
    }

}