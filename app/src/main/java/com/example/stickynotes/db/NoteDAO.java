package com.example.stickynotes.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.stickynotes.db.entity.Note;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
    public long addNote(Note note);

    @Update
    public void updateNote(Note note);

    @Delete
    public void deleteNote(Note note);

    @Query("select * from notes")
    public List<Note> getNotes();

    @Query("select * from notes where note_id=:id")
    public Note getNotebyID(long id);

}
