package com.example.lifecycle_hw611;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        printInfo(new Object(){}.getClass().getEnclosingMethod().getName());
        if (savedInstanceState == null) {
            printInfo("Bundle = null");
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        printInfo(new Object(){}.getClass().getEnclosingMethod().getName());

    }

    @Override
    protected void onResume() {
        super.onResume();
        printInfo(new Object(){}.getClass().getEnclosingMethod().getName());

    }

    @Override
    protected void onPause() {
        super.onPause();
        printInfo(new Object(){}.getClass().getEnclosingMethod().getName());

    }

    @Override
    protected void onStop() {
        super.onStop();
        printInfo(new Object(){}.getClass().getEnclosingMethod().getName());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        printInfo(new Object(){}.getClass().getEnclosingMethod().getName());

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        printInfo(new Object(){}.getClass().getEnclosingMethod().getName());

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        printInfo(new Object(){}.getClass().getEnclosingMethod().getName());

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        printInfo(new Object(){}.getClass().getEnclosingMethod().getName());

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        printInfo(new Object(){}.getClass().getEnclosingMethod().getName());
        return super.onKeyDown(keyCode, event);

    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        printInfo(new Object(){}.getClass().getEnclosingMethod().getName());
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        printInfo(new Object(){}.getClass().getEnclosingMethod().getName());

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        printInfo(new Object(){}.getClass().getEnclosingMethod().getName());
        TextView textView = findViewById(R.id.lifecycle);
        outState.putString("textview",textView.getText().toString());

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        printInfo(new Object(){}.getClass().getEnclosingMethod().getName());
        TextView textView = findViewById(R.id.lifecycle);
        textView.setText(savedInstanceState.getString("textview"));

    }

    public void printInfo(String methodName){
        Log.e("Lifecycle", methodName);
        TextView textView = findViewById(R.id.lifecycle);
        textView.append("\n" + methodName);
    }


    public void onBtnClick(View view) {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        //startActivityForResult(intent, 1);
        startActivity(intent);
    }
}
