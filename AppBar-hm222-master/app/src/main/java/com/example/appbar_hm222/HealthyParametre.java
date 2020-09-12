package com.example.appbar_hm222;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.List;

public class HealthyParametre extends AppCompatActivity {

    private static final String TAG = "Medapp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_parametre);
        final User userObj = (User)getIntent().getExtras().getSerializable("user"); // передача переменных между экранами
        final List<User> userList = (List<User>) getIntent().getExtras().getSerializable("listUsers");

        Calendar c = Calendar.getInstance();



        Button goToMainActivity = (Button) findViewById(R.id.profilBtn2);
        goToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();


            }
        });

        Button saveData = (Button) findViewById(R.id.saveBtn);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    final EditText weightStr = (EditText) findViewById(R.id.weightValue);
                    Double weightVal = Double.parseDouble(String.valueOf(weightStr.getText()));
                    final EditText numRunStr = (EditText) findViewById(R.id.numRunValue);
                    int numRunVal = Integer.parseInt(String.valueOf(numRunStr.getText()));
                    Calendar c = Calendar.getInstance();

                    Healthy healthRecord = new Healthy(weightVal, numRunVal, c.getTime());
                    userObj.healthParam.add(healthRecord);
                    for (int i = 0; i < userList.size(); i++) {
                        if (userList.get(i).name.equals(userObj.name) == true) {
                            userList.set(i, userObj);
                            String str = userList.get(i).healthParam.toString();
                            Toast.makeText(HealthyParametre.this, str, Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                }
                catch (Exception exception){
                Log.e(TAG, "Получено исключение", exception);
                Toast.makeText(HealthyParametre.this,exception.toString(),Toast.LENGTH_LONG).show();

            }



        }
        });
    }
}
