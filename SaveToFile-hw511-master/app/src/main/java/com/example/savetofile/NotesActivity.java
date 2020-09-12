package com.example.savetofile;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class NotesActivity extends AppCompatActivity {
    private static String NOTE_TEXT = "note_text";
    private EditText mInputNote;
    private SharedPreferences myNoteSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        initViews();
        getDateFromSharedPref();
    }
    private void initViews() {
        mInputNote = findViewById(R.id.inputNote);
        myNoteSharedPref = getSharedPreferences("MyNote", MODE_PRIVATE);
    }
    public void clickSave(View view) {

        SharedPreferences.Editor myEditor = myNoteSharedPref.edit();
        String noteTxt = mInputNote.getText().toString();
        myEditor.putString(NOTE_TEXT, noteTxt);
        myEditor.apply();
        Toast.makeText(NotesActivity.this, "данные сохранены", Toast.LENGTH_LONG).show();
    }

    private void getDateFromSharedPref(){
        String noteTxt = myNoteSharedPref.getString(NOTE_TEXT, "");
        try {
            if(noteTxt.equals(null)) {
                noteTxt = "Введите тут первую запись";
            }
            mInputNote.setText(noteTxt);
        }
        catch (Exception e){
            Toast.makeText(NotesActivity.this, "Что то произошло не то", Toast.LENGTH_LONG).show();
        }

    }

}
