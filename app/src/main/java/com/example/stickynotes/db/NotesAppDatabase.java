package com.example.stickynotes.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.stickynotes.db.entity.Note;

@Database(entities = {Note.class},version = 1)
public abstract class NotesAppDatabase extends RoomDatabase {
    public abstract NoteDAO getNoteDAO();
}
