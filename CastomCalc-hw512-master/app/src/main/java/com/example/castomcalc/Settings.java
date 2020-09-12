package com.example.castomcalc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class Settings extends AppCompatActivity implements Serializable {
    public EditText path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        path = findViewById(R.id.PathText);
    }

    public void toMainActive(View view) {

        String strPath = String.valueOf(path.getText());
        Intent intent = new Intent();
        intent.putExtra("path", strPath);
        setResult(RESULT_OK, intent);
        finish();
    }

    
}
