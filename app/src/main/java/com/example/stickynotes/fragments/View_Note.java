package com.example.stickynotes.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.room.Room;

import com.example.stickynotes.R;
import com.example.stickynotes.adapters.NoteAdapter;
import com.example.stickynotes.adapters.RecyclerViewClickListener;
import com.example.stickynotes.db.NotesAppDatabase;
import com.example.stickynotes.db.entity.Note;

import java.util.ArrayList;
import java.util.List;


public class View_Note extends Fragment implements RecyclerViewClickListener {

   NotesAppDatabase notesAppDatabase;
   List<Note> notes;
   NoteAdapter noteAdapter;

    public View_Note(){}

    public static View_Note newInstance() {
        View_Note fragment = new View_Note();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        notesAppDatabase = Room.databaseBuilder(getContext(),NotesAppDatabase.class,"NotesDB").build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_view__note, container, false);

        final ArrayList<Note> notesList = new ArrayList<>();
        RecyclerView recyclerView =v.findViewById(R.id.list_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        noteAdapter = new NoteAdapter(this);
        recyclerView.setAdapter(noteAdapter);

        loadNotes();
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

    void loadNotes(){
        new GetAllNotes().execute();
    }

    @Override
    public void recyclerViewListClicked(View v, final int pos) {
        AlertDialog.Builder a_builder =new AlertDialog.Builder(getActivity());
        a_builder.setMessage(notes.get(pos).getDescription()).setCancelable(false).setPositiveButton("DELETE", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                deleteNote(notes.get(pos));
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

    private class GetAllNotes extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            notes = (List<Note>)notesAppDatabase.getNoteDAO().getNotes();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            noteAdapter.setNotes((ArrayList<Note>) notes);
        }
    }

    private void deleteNote(Note n){
        new DeleteNote().execute(n);
    }

    private class DeleteNote extends AsyncTask<Note,Void,Void>{

        @Override
        protected Void doInBackground(Note... notes) {
            notesAppDatabase.getNoteDAO().deleteNote(notes[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadNotes();
        }
    }
}