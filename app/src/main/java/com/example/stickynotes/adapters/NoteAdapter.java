package com.example.stickynotes.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stickynotes.R;
import com.example.stickynotes.databinding.ListLayoutBinding;
import com.example.stickynotes.db.entity.Note;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {


    private ArrayList<Note> notes;
    private static RecyclerViewClickListener itemListener;

    public NoteAdapter(RecyclerViewClickListener itemListener) {
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_layout,viewGroup,false)
     ListLayoutBinding listLayoutBinding = DataBindingUtil.
             inflate(LayoutInflater.from(viewGroup.getContext()),R.layout.list_layout,viewGroup,false);
     return new NoteViewHolder(listLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i) {

        Note n = notes.get(i);
        noteViewHolder.listLayoutBinding.setNote(n);
    }

    @Override
    public int getItemCount() {
        if(notes != null)
            return notes.size();

        return 0;

    }

    public void setNotes(ArrayList<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }


    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ListLayoutBinding listLayoutBinding;
        public NoteViewHolder(ListLayoutBinding listLayoutBinding) {
            super(listLayoutBinding.getRoot());
            this.listLayoutBinding = listLayoutBinding;
        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition());

        }
    }
}
