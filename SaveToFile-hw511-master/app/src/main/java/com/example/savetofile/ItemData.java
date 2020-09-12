package com.example.savetofile;

import android.graphics.drawable.Drawable;
import android.widget.Button;

public class ItemData {


    private String title;
    private String subtitle;
    private String autor;
    private Button btn;

    public ItemData(String title, String subtitle, String autor) {
        this.title = title;
        this.subtitle = subtitle;
        this.autor = autor;
        this.btn = btn;
    }



    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String toString() {

        return title + "," + subtitle + "," + autor;
    }
}