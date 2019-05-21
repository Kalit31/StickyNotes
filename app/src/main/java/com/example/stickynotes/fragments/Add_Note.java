package com.example.stickynotes.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stickynotes.DatabaseHelper;
import com.example.stickynotes.R;


public class Add_Note extends Fragment {

    DatabaseHelper myDB;
    EditText editText;
    Button save;

    public Add_Note()
    {

    }
    public static Add_Note newInstance() {
        Add_Note fragment = new Add_Note();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.fragment_add__note, container, false);
    editText=view.findViewById(R.id.et);
    save=view.findViewById(R.id.save);
       save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String newEntry= editText.getText().toString();
               if(newEntry.length() != 0)
               {
                   Toast.makeText(getActivity(),"Reached here",Toast.LENGTH_SHORT).show();
                   AddData(newEntry);
                   editText.setText("");

               }
               else
                   Toast.makeText(getActivity(),"Please enter something!",Toast.LENGTH_SHORT).show();
           }
       });
        return view;
    }

    public void AddData(String newEntry)
    {
        boolean insertData =myDB.addData(newEntry);
        if(insertData==true)
            Toast.makeText(getContext(),"Note Added",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getContext(),"Something went wrong!",Toast.LENGTH_SHORT).show();
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
