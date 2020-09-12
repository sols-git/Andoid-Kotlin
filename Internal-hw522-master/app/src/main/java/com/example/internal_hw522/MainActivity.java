package com.example.internal_hw522;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static String acc = "tAccaunt";
    private boolean  statusModeMemory;
    private EditText mLoginEdTxt;
    private EditText mPasswrdEdTxt;
    private CheckBox modeMemory;
    SharedPreferences mySharedPref = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginEdTxt = findViewById(R.id.login);
        mPasswrdEdTxt = findViewById(R.id.password);
        modeMemory = findViewById(R.id.typememory);

        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegistration = findViewById(R.id.btnRegistration);

        statusModeMemory = getSharedInfo();


        modeMemory.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                statusModeMemory = setSharedInfo();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (statusModeMemory){
                        try {
                            singExtMemory();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        singInterMemory();
                    }
                }
            }
        );

        btnRegistration.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (statusModeMemory){
                       reExtMemory();
                   }
                   else
                   {
                       regInterMemory();
                   }
               }
           }
        );

    }

    private void regInterMemory() {
        if (mLoginEdTxt.getText().toString().equals("") || mPasswrdEdTxt.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Поле не должно быто пустым", Toast.LENGTH_LONG).show();
        } else {
            // Создадим файл и откроем поток для записи данных
            FileOutputStream fileOutputStream = null;

            try {
                fileOutputStream = openFileOutput(acc, MODE_PRIVATE);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // Обеспечим переход символьных потока данных к байтовым потокам.
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

            // Запишем текст в поток вывода данных, буферизуя символы так, чтобы обеспечить эффективную запись отдельных символов.
            BufferedWriter bwAccaunt = new BufferedWriter(outputStreamWriter);

            // Осуществим запись данных
            try {
                String accaunt = mLoginEdTxt.getText().toString() + " " + mPasswrdEdTxt.getText().toString();
                bwAccaunt.write(accaunt);

            } catch (IOException e) {
                e.printStackTrace();
            }
            // закроем поток
            try {
                bwAccaunt.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
    private void singInterMemory(){
        if(mLoginEdTxt.getText().toString().equals("") || mPasswrdEdTxt.getText().toString().equals("") ) {
            Toast.makeText(MainActivity.this, "Поле не должно быто пустым", Toast.LENGTH_LONG).show();
        }
        else {

            // Получим входные байты из файла которых нужно прочесть.
            FileInputStream fileInputStream = null;

            try {
                fileInputStream = openFileInput(acc);

            } catch (FileNotFoundException e) {
                Toast.makeText(MainActivity.this, "Зарегистрируйтесь пожалуйста", Toast.LENGTH_LONG).show();
                return;
            }
            // Декодируем байты в символы
            InputStreamReader accReader = new InputStreamReader(fileInputStream);

            // Читаем данные из потока ввода, буферизуя символы так, чтобы обеспечить эффективную запись отдельных символов.
            BufferedReader reader = new BufferedReader(accReader);
            String fromEdLoginText = mLoginEdTxt.getText().toString();
            String fromEdPassText = mPasswrdEdTxt.getText().toString();

            try {

                String line = reader.readLine();
                while (line != null) {
                    String splStr[] = line.split(" ");
                    if (splStr[0].equals(fromEdLoginText)) {
                        Toast.makeText(MainActivity.this, "Логин совпадает", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Логин НЕ совпадает", Toast.LENGTH_LONG).show();
                    }
                    if (splStr[1].equals(fromEdPassText)) {
                        Toast.makeText(MainActivity.this, "Пароль совпадает", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Пароль НЕ совпадает", Toast.LENGTH_LONG).show();
                    }

                    Log.d("Tag", line);
                    line = reader.readLine();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            // закроем поток
            try {
                accReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }
    private boolean getSharedInfo() {
        mySharedPref = getSharedPreferences("state_mode_memory", MODE_PRIVATE);
        boolean stateModeMemory = mySharedPref.getBoolean("state_mode_memory", false);
        modeMemory.setChecked(stateModeMemory);

        return stateModeMemory;
    }
    private boolean setSharedInfo(){
        SharedPreferences finalMySharedPref = mySharedPref;
        SharedPreferences.Editor myEditor = finalMySharedPref.edit();
        boolean saveExternal = modeMemory.isChecked();
        myEditor.putBoolean("state_mode_memory", saveExternal);
        myEditor.apply();

        return saveExternal;
    }

    private void reExtMemory(){
        if (mLoginEdTxt.getText().toString().equals("") || mPasswrdEdTxt.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Поле не должно быто пустым", Toast.LENGTH_LONG).show();
        } else {
            String accaunt = mLoginEdTxt.getText().toString() + " " + mPasswrdEdTxt.getText().toString();
            try {
                updateItemsFile(accaunt, acc);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    private void singExtMemory() throws IOException {
        File file = null;

        file = getItemsFile(false, acc);

        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        String line = null;
        line = reader.readLine();
        if(line == null){
            Toast.makeText(MainActivity.this, "Зарегистрируйтесь пожалуйста", Toast.LENGTH_LONG).show();
            return;
        }
        String fromEdLoginText = mLoginEdTxt.getText().toString();
        String fromEdPassText = mPasswrdEdTxt.getText().toString();
        while (line != null) {
            String splStr[] = line.split(" ");
            if (splStr[0].equals(fromEdLoginText)) {
                Toast.makeText(MainActivity.this, "Логин совпадает", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Логин НЕ совпадает", Toast.LENGTH_LONG).show();
            }
            if (splStr[1].equals(fromEdPassText)) {
                Toast.makeText(MainActivity.this, "Пароль совпадает", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Пароль НЕ совпадает", Toast.LENGTH_LONG).show();
            }

            Log.d("Tag", line);
            line = reader.readLine();

        }

    }

    public File getItemsFile(boolean newFile, String fileName) throws IOException {
        File file = new File(getExternalFilesDir(null), fileName);
        if (!file.exists()) {
            file.createNewFile();
        } else if (newFile) {
            file.delete();
            file.createNewFile();
        }
        return file;
    }

    public void updateItemsFile(String list,String fileName) throws IOException {
        FileWriter writer = new FileWriter(getItemsFile(true, fileName));
        writer.write(list);
        writer.flush();
        writer.close();
    }
}
