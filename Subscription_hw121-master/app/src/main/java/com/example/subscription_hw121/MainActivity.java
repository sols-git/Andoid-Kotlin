package com.example.subscription_hw121;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView resultText = (TextView) findViewById(R.id.textResult);
        final EditText nameText = (EditText) findViewById(R.id.editUserName);
        final EditText emailText = (EditText) findViewById(R.id.editEmail);


/*
        nameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameText.setText("");
            }
        });
        emailText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailText.setText("");


            }
        });
*/
        Button okBtn = (Button) findViewById(R.id.buttonOk);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = getString(R.string.app_str_result_1) + " " + nameText.getText()
                        + " " + getString(R.string.app_str_result_2) + " " + emailText.getText();
                resultText.setText(result);

            }
        });

        Button clearBtn = (Button) findViewById(R.id.buttonClear);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameText.setText("");
                emailText.setText("");
                resultText.setText("");


            }
        });
    }
}
