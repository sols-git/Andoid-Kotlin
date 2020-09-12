package com.example.appbar_hm222;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class CalendarActivity extends AppCompatActivity{
        private Button mChooseStartDate;
        private Button mChooseEndDate;
        private CalendarView mStartDateCalendar;
        private CalendarView mEndtDateCalendar;

        private long mStartDate;
        private String mStartDateTxt;
        private long mEndDate;
        private String mEndDateTxt;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_calendar);
            initViews();
        }
        private void initViews() {
            mChooseStartDate = findViewById(R.id.chooseStartDate);
            mChooseEndDate = findViewById(R.id.chooseEndDate);
            mStartDateCalendar = findViewById(R.id.startDateCalendar);
            mEndtDateCalendar = findViewById(R.id.endtDateCalendar);
            Button mBtnOK = findViewById(R.id.btnOK);

            // Скроем календари при запуске приложения
            mStartDateCalendar.setVisibility(View.GONE);
            mEndtDateCalendar.setVisibility(View.GONE);

            mChooseStartDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mStartDateCalendar.setVisibility(View.VISIBLE);
                    mEndtDateCalendar.setVisibility(View.GONE);
                }
            });

            mChooseEndDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mEndtDateCalendar.setVisibility(View.VISIBLE);
                    mStartDateCalendar.setVisibility(View.GONE);
                }
            });


            mStartDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                    mStartDateTxt = year+"-"+month+"-"+dayOfMonth;
                    String tempStr = getString(R.string.app_chooseStartDate) + mStartDateTxt;
                    mChooseStartDate.setText(tempStr);
                    GregorianCalendar gregorianCalendar = new GregorianCalendar();
                    gregorianCalendar.set(year, month, dayOfMonth);
                    mStartDate = gregorianCalendar.getTimeInMillis();
                    calendarView.setVisibility(View.GONE);
                }
            });

            mEndtDateCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                    mEndDateTxt = year+"-"+month+"-"+dayOfMonth;
                    String tempStr = getString(R.string.app_chooseEndDate) + mEndDateTxt;
                    mChooseEndDate.setText(tempStr);
                    GregorianCalendar gregorianCalendar = new GregorianCalendar();
                    gregorianCalendar.set(year, month, dayOfMonth);
                    mEndDate = gregorianCalendar.getTimeInMillis();
                    calendarView.setVisibility(View.GONE);
                }
            });


            mBtnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mStartDate > mEndDate){
                        Toast.makeText(CalendarActivity.this, "Ошибка", Toast.LENGTH_LONG).show();
                        mChooseStartDate.setText(getString(R.string.app_chooseStartDate));
                        mChooseEndDate.setText(getString(R.string.app_chooseEndDate));
                    } else {
                        Toast.makeText(CalendarActivity.this, "старт: " + mStartDateTxt + " окончаниe: " + mEndDateTxt, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
