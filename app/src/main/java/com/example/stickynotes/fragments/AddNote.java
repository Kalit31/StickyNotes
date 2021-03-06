package com.example.stickynotes.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.room.Room;
import com.example.stickynotes.R;
import com.example.stickynotes.databinding.FragmentAddnoteBinding;
import com.example.stickynotes.db.NotesAppDatabase;
import com.example.stickynotes.db.entity.Note;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;


public class AddNote extends Fragment {

    private FragmentAddnoteBinding fragmentAddnoteBinding;
    private AddNoteClickHandler clickHandler;
    Note note;
    NotesAppDatabase notesAppDatabase;

    public AddNote(){}

    public static AddNote newInstance() {
        AddNote fragment = new AddNote();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        notesAppDatabase = Room.databaseBuilder(getContext(),NotesAppDatabase.class,"NotesDB").allowMainThreadQueries().build();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.fragment_addnote, container, false);
      fragmentAddnoteBinding = DataBindingUtil.setContentView(Objects.requireNonNull(getActivity()),R.layout.fragment_addnote);

       note = new Note();
       fragmentAddnoteBinding.setNote(note);
       clickHandler = new AddNoteClickHandler(getContext());
       fragmentAddnoteBinding.setClickHandler(clickHandler);
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
    public void onDetach() {
        super.onDetach();
    }

    public class AddNoteClickHandler{
        Context context;

        public AddNoteClickHandler(Context context) {
            this.context = context;
        }

        public void  SaveClicked(View view){
            int t=0;
            if(note.getDescription().equals(""))
            {
                Toast.makeText(getActivity(),"Please enter something!",Toast.LENGTH_SHORT).show();
            }
            else {
                List<Note> notes = notesAppDatabase.getNoteDAO().getNotes();
                Iterator<Note> itr = notes.iterator();
                while(itr.hasNext())
                {
                    Note n = itr.next();
                    if(n.getDescription().equals(note.getDescription()))
                    {
                        t=1;
                        Toast.makeText(getActivity(),"Note already added",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                if(t==0)
                    adData(note.getDescription());
                note.setDescription("");
                fragmentAddnoteBinding.setNote(note);
            }
        }

    }
}
