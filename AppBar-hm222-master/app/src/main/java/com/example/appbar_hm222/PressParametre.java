package com.example.appbar_hm222;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class PressParametre extends AppCompatActivity {

    private static final String TAG = "Medapp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_press_parametre);
        final User userObj = (User)getIntent().getExtras().getSerializable("user"); // передача переменных между экранами
        final List<User> userList = (List<User>) getIntent().getExtras().getSerializable("listUsers");


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        final EditText dateTimeStr = (EditText) findViewById(R.id.dataTimeValue);
        dateTimeStr.setText(formattedDate);




        Button goToMainActivity = (Button) findViewById(R.id.profilBtn);
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
            try{
                final TextView hiPressStr = (TextView) findViewById(R.id.hiPressureValue);
                int hiPressVal = Integer.parseInt(String.valueOf(hiPressStr.getText()));
                final TextView lowPressStr = (TextView) findViewById(R.id.lowPressureValue2);
                int lowPressVal = Integer.parseInt(String.valueOf(lowPressStr.getText()));
                final TextView pulseStr = (TextView) findViewById(R.id.pulseVal);
                int pulseVal =  Integer.parseInt(String.valueOf(pulseStr.getText()));
                final CheckBox takch = (CheckBox) findViewById(R.id.checkBoxTakch);
                Boolean tachyCheck = Boolean.parseBoolean(String.valueOf(takch.isChecked()));
                Calendar c = Calendar.getInstance();

                Pressure pressRecord = new Pressure (hiPressVal,lowPressVal,pulseVal,tachyCheck, c.getTime());
                userObj.prerssParam.add(pressRecord);
                for (int i = 0; i < userList.size(); i++) {
                    if(userList.get(i).name.equals(userObj.name) == true) {
                        userList.set(i,userObj);
                        String str = userList.get(i).prerssParam.toString();
                        Toast.makeText(PressParametre.this,str,Toast.LENGTH_LONG).show();
                        break;
                    }
                }

            }
                catch (Exception exception){
                Log.e(TAG, "Получено исключение", exception);
                    Toast.makeText(PressParametre.this,exception.toString(),Toast.LENGTH_LONG).show();

                }




            }
        });
    }
}
