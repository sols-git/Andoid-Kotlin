package com.example.castomcalc;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.Serializable;


public class MainActivity extends AppCompatActivity implements Serializable {

    private TextView stackStr;
    private TextView inputStr;
    private ViewGroup stdCalcLyaout;
    private ViewGroup engCalcLyaout;
    private ToggleButton tggButton;
    private String lastOperator = "="; // последняя операция
    private double result = 0.0;
    private boolean cntrOperarion = false;
    public static final int REQUEST_CODE_PERMISSION_READ_STORAGE = 10;
    private String path;

    private static final String TAG = "CastomCalc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stdCalcLyaout = findViewById(R.id.stdCalc);
        engCalcLyaout = findViewById(R.id.engCalc);
        tggButton = findViewById(R.id.toggleButton);


        stdCalcLyaout.setVisibility(View.GONE);
        engCalcLyaout.setVisibility(View.VISIBLE);
        tggButton.setChecked(true);

        stackStr = findViewById(R.id.stack);
        inputStr = findViewById(R.id.inputkey);
        inputStr.setText("0");


/*


*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        //path = (String) getIntent().getExtras().getSerializable("path");

        try {
            //path = (String) getIntent().getExtras().getSerializable("path");
            int permissionStatus = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permissionStatus == PackageManager.PERMISSION_GRANTED) {

                LoadImg(path);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_PERMISSION_READ_STORAGE);
            }
        }
        catch (Exception exception){
            Log.e(TAG, "Получено исключение", exception);
            Toast.makeText(MainActivity.this,exception.toString(),Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_READ_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                    LoadImg(path);
                } else {
                    // permission denied
                }
                return;
        }
    }

    private double calcByLastOperator(double newOperand) {
        double res = 0.0;
        if (lastOperator.equals("=") || lastOperator.equals("±") || lastOperator.equals("%") || lastOperator.equals("C")) {
            res = newOperand;
        } else {
            switch (lastOperator) {

                case "+":
                    res = result + newOperand;
                    break;
                case "-":
                    res = result - newOperand;
                    break;
                case "÷":
                    res = result / newOperand;
                    break;
                case "x":
                    res = result * newOperand;
                    break;
                default:
                    res = result;
                    break;
            }
        }
        return res;
    }


    private double calc(double newOperand, String newOperator) {

        double res = 0.0;


        switch (newOperator) {
            case "±":
                result = calcByLastOperator(newOperand);
                res = result * -1;
                break;
            case "%":
                result = calcByLastOperator(newOperand);
                res = result / 100;
                break;
            case "C":
                //res = calcByLastOperator(newOperand);
                lastOperator = "=";
                break;
            case "=":
                res = calcByLastOperator(newOperand);
                lastOperator = "=";
                break;
            default:
                if (newOperator.equals("+") || newOperator.equals("-") || newOperator.equals("÷") || newOperator.equals("x")) {
                    res = calcByLastOperator(newOperand);
                }
                break;

        }

        lastOperator = newOperator;
        result = res;
        return result;


    }


    public void onNumberClick(View view) {

        Button button = (Button) view;
        stackStr.append(button.getText());
        if (cntrOperarion) {
            inputStr.setText("");
            cntrOperarion = false;
        }
        inputStr.append(button.getText());
        inputStr.setText(inputStr.getText().toString().replaceFirst("^0+(?!$)", ""));


    }

    public void onOperationClick(View view) {

        Button button = (Button) view;
        String op = button.getText().toString();
        String operandStr = inputStr.getText().toString();
        double operand = Double.valueOf(operandStr);
        double resD = calc(operand, op);
        String resS = String.valueOf(resD);
        switch (op) {
            case "C":
                stackStr.setText("");
                inputStr.setText("0");
                break;
            case "+":
            case "-":
            case "÷":
            case "x":
                stackStr.append(button.getText());
                cntrOperarion = true;
                break;
            case "=":
            case "±":
            case "%":
                stackStr.setText("");
                inputStr.setText(resS);
                cntrOperarion = true;
                break;

        }


    }


    public void onTogglClick(View view) {
        tggButton = (ToggleButton) view;
        if (!tggButton.isChecked())
        {
            stdCalcLyaout.setVisibility(View.VISIBLE);
            engCalcLyaout.setVisibility(View.GONE);
        }
        else {
            stdCalcLyaout.setVisibility(View.GONE);
            engCalcLyaout.setVisibility(View.VISIBLE);
        }

    }

    public void onSettings(View view) {
        Intent intent = new Intent(MainActivity.this, Settings.class);
        startActivityForResult(intent, 1);
        //startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        path = data.getStringExtra("path");

    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    private void LoadImg(String path)
    {

        ImageView view = (ImageView) findViewById(R.id.imageView);
        if (isExternalStorageWritable()) {


            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    path);
            boolean i = file.isFile();

            if (!file.isFile()) {
                Log.e(TAG, "Directory not created");
                Toast.makeText(this, "File not exist", Toast.LENGTH_LONG).show();
            } else {
                Bitmap b = BitmapFactory.decodeFile(file.getAbsolutePath());
                view.setImageBitmap(b);
                Toast.makeText(this, file.getAbsolutePath(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "File Error", Toast.LENGTH_LONG).show();
        }
    }
}
