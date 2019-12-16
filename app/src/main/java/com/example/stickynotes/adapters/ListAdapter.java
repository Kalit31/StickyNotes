package com.example.stickynotes.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.stickynotes.R;
import com.example.stickynotes.db.entity.Note;

import java.util.List;


public class ListAdapter extends ArrayAdapter<Note>
    {
      private  List<Note> items;
      private Activity context;

        public ListAdapter(List<Note> items, Activity context)
        {
            super(context, R.layout.list_layout,items);
            this.items = items;
            this.context = context;
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater = context.getLayoutInflater();
            View row = inflater.inflate(R.layout.list_layout, parent, false);
            TextView note = row.findViewById(R.id.note);
            note.setText(items.get(position).getDescription());
            return row;
        }
    }