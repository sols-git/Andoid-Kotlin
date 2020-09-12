package com.example.customadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lw = findViewById(R.id.list);
        ArrayList<ItemData> itemData = new ArrayList<>();
        itemData.add(new ItemData(getDrawable(R.mipmap.ic_health), getString(R.string.app_name_medapp), getString(R.string.sub_13)));
        itemData.add(new ItemData(getDrawable(R.mipmap.ic_checkbox), getString(R.string.checkboxapp), getString(R.string.sub_21)));
        itemData.add(new ItemData(getDrawable(R.mipmap.ic_spiner), getString(R.string.app_name_spiner), getString(R.string.sub_21)));
        itemData.add(new ItemData(getDrawable(R.mipmap.ic_tasks), getString(R.string.app_taskdate), getString(R.string.sub_21)));
        itemData.add(new ItemData(getDrawable(R.mipmap.ic_notepad), getString(R.string.notepad), getString(R.string.sub_22)));



        ItemsDataAdapter adapter = new ItemsDataAdapter(this, itemData );
        lw.setAdapter(adapter);
    }



    /*

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }





    public void onClickNotePadBtnMenu(MenuItem item) {
        Toast.makeText(MainActivity.this, "Отркыть записную книжку", Toast.LENGTH_LONG).show();
        Intent intentNotes = new Intent(MainActivity.this, NotesActivity.class);
        startActivity(intentNotes);
    }

    public void onClickAltCheck(MenuItem item) {
        Toast.makeText(MainActivity.this, "Отркыть взаимоисключающий checkbox", Toast.LENGTH_LONG).show();
        Intent intentNotes = new Intent(MainActivity.this, AltChBoxActivity.class);
        startActivity(intentNotes);
    }

    public void onClickSpinerStart(MenuItem item) {
        Toast.makeText(MainActivity.this, "Отркыть Spiner", Toast.LENGTH_LONG).show();
        Intent intentNotes = new Intent(MainActivity.this, SpinerActivity.class);
        startActivity(intentNotes);
    }

    public void onClickTaskStart(MenuItem item) {
        Toast.makeText(MainActivity.this, "Отркыть задачник", Toast.LENGTH_LONG).show();
        Intent intentNotes = new Intent(MainActivity.this, CalendarActivity.class);
        startActivity(intentNotes);
    }
    public void onClickMedapp(MenuItem item) {
        Toast.makeText(MainActivity.this, "Отркыть медицинское приложение", Toast.LENGTH_LONG).show();
        Intent intentNotes = new Intent(MainActivity.this, MedActivity.class);
        startActivity(intentNotes);
    }
    */
}
