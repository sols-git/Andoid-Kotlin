package com.example.customadapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button del = findViewById(R.id.delete);
        ListView lw = findViewById(R.id.list);
        ArrayList<ItemData> itemData = new ArrayList<>();
        itemData.add(new ItemData(getDrawable(R.mipmap.ic_health), getString(R.string.app_name_medapp), getString(R.string.sub_13),del));
        itemData.add(new ItemData(getDrawable(R.mipmap.ic_checkbox), getString(R.string.checkboxapp), getString(R.string.sub_21),del));
        itemData.add(new ItemData(getDrawable(R.mipmap.ic_spiner), getString(R.string.app_name_spiner), getString(R.string.sub_21),del));
        itemData.add(new ItemData(getDrawable(R.mipmap.ic_tasks), getString(R.string.app_taskdate), getString(R.string.sub_21),del));
        itemData.add(new ItemData(getDrawable(R.mipmap.ic_notepad), getString(R.string.notepad), getString(R.string.sub_22),del));


        final ItemsDataAdapter adapter = new ItemsDataAdapter(this, itemData );
        lw.setAdapter(adapter);
        // При долгом тапе по элементу списка будем удалять его
        lw.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showItemData(position,adapter);
                return true;
            }
        });




    }


    // Покажем сообщение с данными
    private void showItemData(int position, ItemsDataAdapter adapter) {
        ItemData iteData = adapter.getItem(position);
        Toast.makeText(MainActivity.this,
                "Title: " + iteData.getTitle() + "\n" +
                        "Subtitle: " + iteData.getSubtitle() + "\n",
                Toast.LENGTH_SHORT).show();
    }


}
