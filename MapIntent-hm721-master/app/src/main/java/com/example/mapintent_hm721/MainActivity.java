package com.example.mapintent_hm721;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText adress = findViewById(R.id.pininfo);
        Button btnOpenMap = findViewById(R.id.btnmapopen);
        btnOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri;
                String pinstr;
                String adrr = adress.getText().toString();
                if(adrr.replaceAll("[\\d]+\\.[\\d]+\\,*\\s*","").equals("")){
                    pinstr = "geo:" + adrr;

                }
                else {
                    pinstr = "geo:?q=" + adrr;
                }
                uri = Uri.parse(pinstr);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
}
