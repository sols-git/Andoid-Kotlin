package com.example.appbar_hm222;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_open_notes) {
            Toast.makeText(MainActivity.this, "Отркыть записную книжку", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */


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
}
