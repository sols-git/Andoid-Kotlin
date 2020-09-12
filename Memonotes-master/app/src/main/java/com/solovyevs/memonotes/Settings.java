package com.solovyevs.memonotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void onClicktoPinEdit(View view) {

        Intent intent = new Intent(Settings.this, PincodeEditor.class);
        startActivity(intent);
        finish();
    }
}
