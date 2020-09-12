package com.example.intents_hw711;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSinhro = findViewById(R.id.btnsinhro);

        btnSinhro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SYNC);
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                if (6 < hour && hour < 14){

                    //Toast.makeText(MainActivity.this, "Утро", Toast.LENGTH_LONG).show();
                    intent.setData(Uri.parse("http://morning"));
                }
                if (14 < hour && hour < 15){

                    //Toast.makeText(MainActivity.this, "День", Toast.LENGTH_LONG).show();
                    intent.setData(Uri.parse("http://afternoon"));
                }

                if (15 < hour && hour < 24){

                    //Toast.makeText(MainActivity.this, "Утро", Toast.LENGTH_LONG).show();
                    intent.setData(Uri.parse("http://evening"));
                }


                startActivity(intent);
            }
        });
    }
}
