package com.example.customadapter;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class MedActivity extends AppCompatActivity implements Serializable {
    public List<User> userList = new ArrayList<>();
    public User userObj;
    private static final String TAG = "Medapp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medapp);


        Button goToPrssActivity = (Button) findViewById(R.id.prerssRecBtn);
        goToPrssActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MedActivity.this, PressParametre.class);
                    intent.putExtra("user", userObj);
                    intent.putExtra("listUsers", (Serializable) userList);
                    startActivity(intent);
                }
                catch (Exception exception){
                    Log.e(TAG, "Получено исключение", exception);
                    Toast.makeText(MedActivity.this,exception.toString(),Toast.LENGTH_LONG).show();

                }


            }
        });

        Button goToHealthActivity = (Button) findViewById(R.id.healthyRecBtn);
        goToHealthActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MedActivity.this, HealthyParametre.class);
                    intent.putExtra("user", userObj);
                    intent.putExtra("listUsers", (Serializable) userList);
                    startActivity(intent);
                }
                catch (Exception exception){
                    Log.e(TAG, "Получено исключение", exception);
                    Toast.makeText(MedActivity.this,exception.toString(),Toast.LENGTH_LONG).show();

                }

            }
        });
        Button saveData = (Button) findViewById(R.id.saveBtn);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final EditText userName = (EditText) findViewById(R.id.editName);
                    final EditText userAge = (EditText) findViewById(R.id.editAge);
                    String t = String.valueOf(userAge.getText());
                    int age = Integer.parseInt(t);
                    String name = String.valueOf(userName.getText());
                    List<Pressure> prerssParam = new ArrayList<>();
                    ;
                    List<Healthy> healthParam = new ArrayList<>();
                    ;

                    userObj = new User(name, age, prerssParam, healthParam);
                    userList.add(userObj);
                    Toast.makeText(MedActivity.this, userList.toString(), Toast.LENGTH_LONG).show();
                }
                catch (Exception exception){
                    Log.e(TAG, "Получено исключение", exception);
                    Toast.makeText(MedActivity.this,exception.toString(),Toast.LENGTH_LONG).show();
                }

            }
        });



    }

}
