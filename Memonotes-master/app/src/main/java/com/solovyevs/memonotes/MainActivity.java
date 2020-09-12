package com.solovyevs.memonotes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Objects;

import static com.solovyevs.memonotes.Constants.*;


public class MainActivity extends AppCompatActivity {
    private ArrayList<Note> itemData;
    private NotesAdapter adapter;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        clickListeners();

    }

    public void init()
    {
        itemData = App.getNoteRepository().getArray();
        listview = findViewById(R.id.list);
        adapter = new NotesAdapter(this, itemData);
        adapter.listRevers(itemData);
        listview.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        Note note = (Note) Objects.requireNonNull(data.getExtras()).getSerializable(Note.class.getSimpleName());
        if(note.getTitle().equals(EMPTY) && note.getBodyNote().equals(EMPTY))
        {
            return;
        }
        if(requestCode == NEW_NOTE){
            adapter.addItem(note);
            App.getNoteRepository().insertNote(note);
        }

        if(requestCode == EXIST_NOTE){
            int position = data.getIntExtra(NULL_POSITION_STR,NULL_POSITION);
            adapter.removeItem(position);
            adapter.addItem(note);
            App.getNoteRepository().updateNote(note);
        }

        adapter.listRevers(itemData);

    }


    public void clickListeners()
    {
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int localpos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        App.getNoteRepository().deleteNote(itemData.get(localpos));
                        adapter.removeItem(localpos);
                        itemData = App.getNoteRepository().getArray();


                    }
                });

                builder.setNeutralButton(R.string.share, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String msg = itemData.get(localpos).getTitle() + "\n" + itemData.get(localpos).getBodyNote() +
                                "\n" + convertLongDateToString(itemData.get(localpos).getDeadline());
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT, msg);
                        Intent chosenIntent = Intent.createChooser(intent, NOTE);
                        startActivity(chosenIntent);

                    }
                });


                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            }
        });



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Intent intent = new Intent(MainActivity.this, NewNote.class);
                Note note = (Note) adapter.getItem(position);
                intent.putExtra(EXIST_NOTE_STR, note);
                intent.putExtra(POSITION_STR,position);
                startActivityForResult(intent, EXIST_NOTE);
            }
        });



    }

    public void onClickEditPin(View view) {
        Intent intent = new Intent(MainActivity.this, Settings.class);
        startActivity(intent);
    }

    public void onClickAddItem(View view) {
        Intent intent = new Intent(MainActivity.this, NewNote.class);
        intent.putExtra(NULL_POSITION_STR,NULL_POSITION);
        startActivityForResult(intent, NEW_NOTE);
    }

    private String convertLongDateToString(Long calendar)
    {
        return DateUtils.formatDateTime(this, calendar,
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME);
    }



}
