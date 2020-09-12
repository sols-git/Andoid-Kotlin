package com.solovyevs.memonotes;


import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.solovyevs.memonotes.Constants.*;
import static com.solovyevs.memonotes.Note.convertDateToLong;

public class NewNote extends AppCompatActivity   implements DatePickerDialog.OnDateSetListener{
    private GregorianCalendar gregorianCalendar =new GregorianCalendar();
    private Long dateAndTime = NEVER_HAPPEN;
    private TextView choosDateTime;
    private Button timechoose;
    private Button datechoose;
    private Note newNote;
    private EditText titleNote;
    private EditText bodyNote;
    private CheckBox dealdileStat;
    private int position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        init();
    }

    protected void init()
    {

        choosDateTime = findViewById(R.id.newdeadline);
        timechoose = findViewById(R.id.timechoose);
        datechoose = findViewById(R.id.datechoose);
        titleNote = findViewById(R.id.titlefornewnote);
        bodyNote = findViewById(R.id.bodynewnote);
        dealdileStat = findViewById(R.id.check_box_deadline);


        Intent intent = getIntent();
        position = intent.getIntExtra(NULL_POSITION_STR, NULL_POSITION);


        if(position != NULL_POSITION) {
            newNote = (Note) intent.getSerializableExtra(EXIST_NOTE_STR);
            titleNote.setText(newNote.getTitle());
            bodyNote.setText(newNote.getBodyNote());
            if(newNote.getDeadline() != NEVER_HAPPEN) {
                choosDateTime.setText(convertLongDateToString(newNote.getDeadline()));
                dealdileStat.setChecked(true);
                dateAndTime = newNote.getDeadline();
                timechoose.setVisibility(View.VISIBLE);
                datechoose.setVisibility(View.VISIBLE);
                choosDateTime.setVisibility(View.VISIBLE);
            }
        }
    }

    public void OnClikChBxDline(View view) {
        CheckBox chbxDlinne = (CheckBox) view;

        if(chbxDlinne.isChecked()){
            timechoose.setVisibility(View.VISIBLE);
            datechoose.setVisibility(View.VISIBLE);
            choosDateTime.setVisibility(View.VISIBLE);
            dateAndTime = convertDateToLong(new GregorianCalendar());
            choosDateTime.setText(convertLongDateToString(dateAndTime));
        }
        else{
            timechoose.setVisibility(View.GONE);
            datechoose.setVisibility(View.GONE);
            choosDateTime.setVisibility(View.GONE);
            dateAndTime = NEVER_HAPPEN;
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    public void OnClickDateChoose(View view) {
        new DatePickerDialog(NewNote.this, d,
                gregorianCalendar.get(Calendar.YEAR),
                gregorianCalendar.get(Calendar.MONTH),
                gregorianCalendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    public void OnClickTimeChoose(View view) {
        new TimePickerDialog(NewNote.this, t,
                gregorianCalendar.get(Calendar.HOUR_OF_DAY),
                gregorianCalendar.get(Calendar.MINUTE), true)
                .show();
    }


    private String convertLongDateToString(Long calendar)
    {
        return DateUtils.formatDateTime(this, calendar,
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME);
    }

    // установка обработчика выбора времени
    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            gregorianCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            gregorianCalendar.set(Calendar.MINUTE, minute);
            dateAndTime = gregorianCalendar.getTimeInMillis();
            choosDateTime.setText(convertLongDateToString(dateAndTime));
        }
    };

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            gregorianCalendar.set(Calendar.YEAR, year);
            gregorianCalendar.set(Calendar.MONTH, monthOfYear);
            gregorianCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            choosDateTime.setText(convertLongDateToString(gregorianCalendar.getTimeInMillis()));
            dateAndTime = gregorianCalendar.getTimeInMillis();
            choosDateTime.setText(convertLongDateToString(dateAndTime));
        }
    };



    public void OnClickSave(View view) {
        Intent intent = new Intent();
        String newTitle = titleNote.getText().toString();
        String newBodyNote = bodyNote.getText().toString();
        Long newDeadline = dateAndTime;


        if(position == NULL_POSITION) {
            ArrayList<Note> itemData;
            itemData = App.getNoteRepository().getArray();
            int size = itemData.size();
            int id = 1;
            if(size > 0 ) {
                id = itemData.get(size - 1).getId() + 1;
            }


            Long newDateUpdate = convertDateToLong(new GregorianCalendar());
            Long newDateCreate = convertDateToLong(new GregorianCalendar());
            newNote = new Note(newTitle, newBodyNote, newDeadline, newDateUpdate, newDateCreate);
            newNote.setId(id);

            intent.putExtra(Note.class.getSimpleName(), newNote);
            setResult(NEW_NOTE, intent);
        }
        else
        {
            newNote.setTitle(newTitle);
            newNote.setBodyNote(newBodyNote);
            newNote.setDeadline(dateAndTime);
            newNote.setDateUpdate(convertDateToLong(new GregorianCalendar()));

            intent.putExtra(Note.class.getSimpleName(), newNote);
            intent.putExtra(POSITION_STR, position);

            setResult(EXIST_NOTE, intent);
        }
        finish();
    }

    public void onClickBack(View view) {
        finish();
    }


}
