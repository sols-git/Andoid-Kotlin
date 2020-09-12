package com.example.engcalc;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {

    private TextView stackStr;
    private TextView inputStr;
    private ViewGroup stdCalcLyaout;
    private ViewGroup engCalcLyaout;
    private ToggleButton tggButton;
    private String lastOperator = "="; // последняя операция
    private double result = 0.0;
    private boolean cntrOperarion = false;


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
}
