package com.example.stickynotes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.room.Room;
import com.example.stickynotes.R;
import com.example.stickynotes.db.NotesAppDatabase;
import com.example.stickynotes.db.entity.Note;

import java.util.Iterator;
import java.util.List;


public class Add_Note extends Fragment {


    EditText editText;
    Button save;
    NotesAppDatabase notesAppDatabase;

    public Add_Note(){}

    public static Add_Note newInstance() {
        Add_Note fragment = new Add_Note();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        notesAppDatabase = Room.databaseBuilder(getContext(),NotesAppDatabase.class,"NotesDB").allowMainThreadQueries().build();
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
               int t=0;
               String newEntry= editText.getText().toString();
               if(newEntry.equals(""))
               {
                   Toast.makeText(getActivity(),"Please enter something!",Toast.LENGTH_SHORT).show();
               }
               else {
                   List<Note> notes = notesAppDatabase.getNoteDAO().getNotes();
                   Iterator<Note> itr = notes.iterator();
                   while(itr.hasNext())
                   {
                       Note n = itr.next();
                       if(n.getDescription().equals(newEntry))
                       {
                           t=1;
                           Toast.makeText(getActivity(),"Note already added",Toast.LENGTH_SHORT).show();
                           break;
                       }
                   }
                    if(t==0)
                       adData(newEntry);
                   editText.setText("");
               }
           }
       });
        return view;
    }

    public void adData(String newEntry){
        long id = notesAppDatabase.getNoteDAO().addNote(new Note(newEntry,0));
        Toast.makeText(getContext(),"Note Added",Toast.LENGTH_SHORT).show();
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