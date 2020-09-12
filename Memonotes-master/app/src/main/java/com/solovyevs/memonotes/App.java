package com.solovyevs.memonotes;

import android.app.Application;


public class App extends Application {

    private static NoteRepository noteRepository;
    static KeyStoreAccess keystore;
    @Override
    public void onCreate() {
        super.onCreate();
        keystore = new KeyStoreAccess(this);
        noteRepository = new NoteRepository( this);
    }

    public static KeyStoreAccess getKeystore() {
        return keystore;
    }

    public static NoteRepository getNoteRepository(){
        return noteRepository;
    }



}
