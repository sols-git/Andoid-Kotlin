package com.solovyevs.memonotes;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Query("SELECT * FROM note WHERE id = :id")
    Note getById(long id);

    @Insert
    void insert(Note employee);

    @Update
    void update(Note employee);

    @Delete
    void delete(Note employee);

    @Query("DELETE FROM note WHERE id = :id")
    void deleteById(int id);



}
