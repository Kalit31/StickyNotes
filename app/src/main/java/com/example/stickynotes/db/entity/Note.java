package com.example.stickynotes.db.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.android.databinding.library.baseAdapters.BR;

@Entity(tableName = "notes")
public class Note extends BaseObservable {

    @ColumnInfo(name = "note_desc")
    private String description;

    @ColumnInfo(name = "note_id")
    @PrimaryKey(autoGenerate = true)
    private long id;

    @Ignore
    public Note() {
    }

    public Note(String description, long id) {
        this.description = description;
        this.id = id;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }


    @Bindable
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

}
