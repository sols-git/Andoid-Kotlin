package ru.netology.lists;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {
    private static String NOTE_TEXT = "data";
    private SharedPreferences myNoteSharedPref;
    List<Map<String, String>> content = new ArrayList<>();
    ArrayList <Integer> index = new ArrayList<>();
    BaseAdapter listContentAdapter;
    SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView listview = findViewById(R.id.list);
        fillListAdapt(getData());

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                content.remove(position);
                index.add(position);
                listContentAdapter.notifyDataSetChanged();
            }
        });


        swipeLayout = findViewById(R.id.swiperefresh);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            // Будет вызван, когда пользователь потянет список вниз
            @Override
            public void onRefresh() {
                content.clear();
                listContentAdapter.notifyDataSetChanged();
                fillListAdapt(getData());
                swipeLayout.setRefreshing(false);
            }
        });

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList("rmItem", index);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        index = savedInstanceState.getIntegerArrayList("rmItem");
        for (int rmItem : index)
        {
           try{
               content.remove(rmItem);
           }
           catch (IndexOutOfBoundsException e)
           {
               Toast.makeText(ListViewActivity.this, e.toString(), Toast.LENGTH_LONG).show();
           }
        }
        listContentAdapter.notifyDataSetChanged();


    }

    @NonNull
    private BaseAdapter createAdapter(List<Map<String, String>> values) {
        return new SimpleAdapter (this, values, R.layout.listview_constr,
                new String []{"text","size"}, new int[]{ R.id.top, R.id.bottom });
    }

    @NonNull
    private List<Map<String, String>>  prepareContent(String textContent) {
        String[] arrayContent = textContent.split("\n\n");
        for (int i = 0; i < arrayContent.length; i++)
        {
            Map<String, String> rowStr = new HashMap<>();
            rowStr.put("text",arrayContent[i]);
            rowStr.put("size", String.valueOf(arrayContent[i].length()));
            content.add(rowStr);

        }

        return content;
    }

    private String getData(){
        myNoteSharedPref = getSharedPreferences("Largtext", MODE_PRIVATE);
        String savingText = myNoteSharedPref.getString(NOTE_TEXT, "");
        if(savingText.equals("")) {
            savingText = getString(R.string.large_text);
            saveData(savingText);
            Toast.makeText(ListViewActivity.this, "данные сохранены", Toast.LENGTH_LONG).show();
        }

        return savingText;
    }

    private void saveData(String data)
    {
        SharedPreferences.Editor myEditor = myNoteSharedPref.edit();
        myEditor.putString(NOTE_TEXT, data);
        myEditor.apply();
    }

    private void fillListAdapt(String content)
    {

        ListView list = findViewById(R.id.list);
        List<Map<String, String>> values = prepareContent(content);
        listContentAdapter = createAdapter(values);
        list.setAdapter(listContentAdapter);

    }



}
