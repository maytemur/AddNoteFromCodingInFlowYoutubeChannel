package com.maytemur.notecodingflow;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM not_tablosu")
    void deleteAllNotes();

    @Query("SELECT * FROM not_tablosu ORDER BY oncelik_derecesi DESC")
    LiveData<List<Note>> getAllNotes();
    //Livedata sayesinde anlık değişikler güncellenerek gösterilecek

}
