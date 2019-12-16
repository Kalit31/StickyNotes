package com.example.stickynotes.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.room.Room;

import com.example.stickynotes.R;
import com.example.stickynotes.adapters.ListAdapter;
import com.example.stickynotes.db.NotesAppDatabase;
import com.example.stickynotes.db.entity.Note;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class View_Note extends Fragment {

   NotesAppDatabase notesAppDatabase;

    public View_Note(){}

    public static View_Note newInstance() {
        View_Note fragment = new View_Note();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        notesAppDatabase = Room.databaseBuilder(getContext(),NotesAppDatabase.class,"NotesDB").allowMainThreadQueries().build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_view__note, container, false);

        final ArrayList<Note> notesList = new ArrayList<>();
        ListView listView =v.findViewById(R.id.list_view);

        Log.d("test","here1");
        List<Note> notes =  notesAppDatabase.getNoteDAO().getNotes();
        if(notes.isEmpty())
        {
            Toast.makeText(getActivity(),"No Notes!!", Toast.LENGTH_SHORT).show();
            Log.d("test","here2");
        }
        else
        {
            Iterator<Note> itr = notes.iterator();
            Log.d("test","here3");
            while(itr.hasNext())
            {
                Note n = itr.next();
                notesList.add(n);
            }
        }

        ListAdapter adapter=new ListAdapter(notesList,getActivity());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id){
                AlertDialog.Builder a_builder =new AlertDialog.Builder(getActivity());
                a_builder.setMessage(notesList.get(position).getDescription()).setCancelable(false).setPositiveButton("DELETE", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                            notesAppDatabase.getNoteDAO().deleteNote(notesList.get(position));
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
    public void onAttach(Context context){
        super.onAttach(context);
    }

    @Override
    public void onDetach(){
        super.onDetach();
    }
}