package com.example.calculator_hm311;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void onKey1(View view) {
        TextView clcDisplay = findViewById(R.id.display);
        clcDisplay.setText("1");
    }

    public void onKey2(View view) {
        TextView clcDisplay = findViewById(R.id.display);
        clcDisplay.setText("2");
    }

    public void onKey3(View view) {
        TextView clcDisplay = findViewById(R.id.display);
        clcDisplay.setText("3");
    }

    public void onKey4(View view) {
        TextView clcDisplay = findViewById(R.id.display);
        clcDisplay.setText("4");
    }

    public void onKey5(View view) {
        TextView clcDisplay = findViewById(R.id.display);
        clcDisplay.setText("5");
    }

    public void onKey6(View view) {
        TextView clcDisplay = findViewById(R.id.display);
        clcDisplay.setText("6");
    }

    public void onKey7(View view) {
        TextView clcDisplay = findViewById(R.id.display);
        clcDisplay.setText("7");
    }

    public void onKey8(View view) {
        TextView clcDisplay = findViewById(R.id.display);
        clcDisplay.setText("8");
    }

    public void onKey9(View view) {

        TextView clcDisplay = findViewById(R.id.display);clcDisplay.setText("9");
    }

    public void onKey0(View view) {
        TextView clcDisplay = findViewById(R.id.display);
        clcDisplay.setText("0");
    }

    public void onKeyDot(View view) {
        TextView clcDisplay = findViewById(R.id.display);
        clcDisplay.setText(".");
    }


}
