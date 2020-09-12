package ru.netology.lists;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ViewGroup layout = findViewById(R.id.linearLayout);
        setSupportActionBar(toolbar);

        ListView list = findViewById(R.id.list);

        List<Map<String, String>> values = prepareContent();

        BaseAdapter listContentAdapter = createAdapter(values);

        list.setAdapter(listContentAdapter);
    }

    @NonNull
    private BaseAdapter createAdapter(List<Map<String, String>> values) {

        return new SimpleAdapter (this, values, R.layout.listview_constr,
                new String []{"text","size"}, new int[]{ R.id.top, R.id.bottom });
    }

    @NonNull
    private List<Map<String, String>>  prepareContent() {

        List<Map<String, String>> content = new ArrayList<>();

        String[] arrayContent = getString(R.string.large_text).split("\n\n");
        for (int i = 0; i < arrayContent.length; i++)
        {
            Map<String, String> rowStr = new HashMap<>();
            rowStr.put("text",arrayContent[i]);
            rowStr.put("size", String.valueOf(arrayContent[i].length()));
            content.add(rowStr);

        }

        return content;
    }
}
