package com.example.savetofile;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button reset = findViewById(R.id.reset);
        Button newrec = findViewById(R.id.newrec);
        ListView lw = findViewById(R.id.list);
        final ArrayList<ItemData> itemData = new ArrayList<>();
        final ItemsDataAdapter adapter = new ItemsDataAdapter(this, itemData);


        loadItems(itemData);


        lw.setAdapter(adapter);
        lw.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showItemData(position, adapter);
                return true;
            }
        });

        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    adapter.removeItem(position);
                    newRecord(parent.getContext(), itemData);
                    adapter.notifyDataSetChanged();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    resetList();
                    loadItems(itemData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
            }
        });

        newrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemData.add(new ItemData("new app", "new lesson" ,"new autor"));
                newRecord(v.getContext(),itemData);
                adapter.notifyDataSetChanged();
            }
        });



    }



    // Покажем сообщение с данными
    private void showItemData(int position, ItemsDataAdapter adapter) {
        ItemData iteData = adapter.getItem(position);
        Toast.makeText(MainActivity.this,
                "Title: " + iteData.getTitle() + "\n" +
                        "Subtitle: " + iteData.getSubtitle() + "\n" +
                            "Autor:" + iteData.getAutor(),
                Toast.LENGTH_SHORT).show();
    }

    private  void resetList() throws IOException {

        String data1 = getString(R.string.app_name_medapp) + "," + getString(R.string.sub_13) + "," + getString(R.string.autor1);
        String data2 = getString(R.string.checkboxapp) + "," + getString(R.string.sub_21) + "," + getString(R.string.autor2);
        String data3 = getString(R.string.app_name_spiner) + "," + getString(R.string.sub_21) + "," + getString(R.string.autor3);
        String data4 = getString(R.string.app_taskdate) + "," + getString(R.string.sub_21) + "," + getString(R.string.autor4);
        String data5 = getString(R.string.notepad) + "," + getString(R.string.sub_22) + "," + getString(R.string.autor5);

        String list = data1 + ";" + data2 + ";" + data3 + ";" + data4 + ";" + data5;

        updateItemsFile(this, list);


    }
    private void loadItems(ArrayList<ItemData> itemData) {
        itemData.clear();
        ArrayList<String> itemsList = new ArrayList<>();


        try {
            File file = getItemsFile(this, false);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            if(line == null){
                Toast.makeText(MainActivity.this, "Пустой список в файле, нажмите RESET для перезаписи", Toast.LENGTH_SHORT).show();
                return;
            }
            for (String outline : line.split(";")) {
                itemsList.add(outline);
                ArrayList<String> subitemsList = new ArrayList<>();
                for (String inline : outline.split(",")) {
                    subitemsList.add(inline);

                }

                itemData.add(new ItemData(subitemsList.get(0), subitemsList.get(1),subitemsList.get(2)));
            }



        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public  void newRecord(Context context, ArrayList<ItemData> itemData) {

        try {
            updateItemsFile(context,lstConvertToString(itemData));
            loadItems(itemData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String lstConvertToString(ArrayList<ItemData> itemData) {

        String line = "";
        for(ItemData data : itemData){
            line = line + data + ";";
        }
        return line.substring(0,line.length()-1);
    }
    public File getItemsFile(Context context, boolean newFile) throws IOException {
        File file = new File(context.getExternalFilesDir(null), "items.txt");
        if (!file.exists()) {
            file.createNewFile();
        } else if (newFile) {
            file.delete();
            file.createNewFile();
        }
        return file;
    }

    public void updateItemsFile(Context context, String list) throws IOException {
        FileWriter writer = new FileWriter(getItemsFile(context, true));
        writer.write(list);
        writer.flush();
        writer.close();
    }



}
