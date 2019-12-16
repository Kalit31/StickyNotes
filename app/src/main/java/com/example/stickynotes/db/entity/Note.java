package com.example.stickynotes.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
