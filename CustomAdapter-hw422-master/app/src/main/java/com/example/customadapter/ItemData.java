package com.example.customadapter;

import android.graphics.drawable.Drawable;
import android.widget.Button;

public class ItemData {

    private Drawable image;
    private String title;
    private String subtitle;
    private Button btnDel;
    //private boolean checked;

    public ItemData(Drawable image, String title, String subtitle,Button btnDel) {
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.btnDel = btnDel;
        //this.checked = checked;
    }

    public Drawable getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public Button getBtnDel() {
        return btnDel;
    }


/*public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }*/
}