package com.solovyevs.memonotes;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;


public class NoteRepository implements NoteRepositorieble {

    private AppDatabase database;
    private NoteDao noteDao;


    public NoteRepository(Context context) {
        this.database = Room.databaseBuilder(context, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }


    @Override
    public AppDatabase getDatabase() {
        return database;
    }

    public ArrayList<Note> getArray(){
        database = App.getNoteRepository().getDatabase();
        noteDao = database.noteDao();
        return (ArrayList<Note>) noteDao.getAll();
    }

    public void insertNote(Note note)
    {
        noteDao.insert(note);
    }
    public void updateNote(Note note)
    {
        noteDao.update(note);
    }

    public void deleteNote (Note localpos)
    {
        noteDao.deleteById(localpos.getId());


    }




}
