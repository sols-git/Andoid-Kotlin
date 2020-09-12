package com.example.internal_hw521;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    static String fileLogin = "login";
    static String filePssw = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText mLoginEdTxt = findViewById(R.id.login);
        final EditText mPasswrdEdTxt = findViewById(R.id.password);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegistration = findViewById(R.id.btnRegistration);

        btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mLoginEdTxt.getText().toString().equals("") || mPasswrdEdTxt.getText().toString().equals("") ) {
                        Toast.makeText(MainActivity.this, "Поле не должно быто пустым", Toast.LENGTH_LONG).show();
                    }
                    else {

                        // Получим входные байты из файла которых нужно прочесть.
                        FileInputStream fileInputStreamLogin = null;
                        FileInputStream fileInputStreamPassw = null;

                        try {
                            fileInputStreamLogin = openFileInput(fileLogin);
                            fileInputStreamPassw = openFileInput(filePssw);

                        } catch (FileNotFoundException e) {
                            Toast.makeText(MainActivity.this, "Зарегистрируйтесь пожалуйста", Toast.LENGTH_LONG).show();
                            return;
                        }
                        // Декодируем байты в символы
                        InputStreamReader inputStreamReaderLogin = new InputStreamReader(fileInputStreamLogin);
                        InputStreamReader inputStreamReaderPassw = new InputStreamReader(fileInputStreamPassw);

                        // Читаем данные из потока ввода, буферизуя символы так, чтобы обеспечить эффективную запись отдельных символов.
                        BufferedReader rdrLogin = new BufferedReader(inputStreamReaderLogin);
                        BufferedReader rdrPassw = new BufferedReader(inputStreamReaderPassw);

                        try {
                            String fromFile = rdrLogin.readLine();
                            String fromEdText = mLoginEdTxt.getText().toString();
                            if (fromFile.equals(fromEdText)) {
                                Toast.makeText(MainActivity.this, "Логин совпадает", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Логин НЕ совпадает", Toast.LENGTH_LONG).show();
                            }
                            fromFile = rdrPassw.readLine();
                            fromEdText = mPasswrdEdTxt.getText().toString();

                            if (fromFile.equals(fromEdText)) {
                                Toast.makeText(MainActivity.this, "Пароль совпадает", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Пароль НЕ совпадает", Toast.LENGTH_LONG).show();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // закроем поток
                        try {
                            rdrLogin.close();
                            rdrPassw.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                }
            }
        );

        btnRegistration.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(mLoginEdTxt.getText().toString().equals("") || mPasswrdEdTxt.getText().toString().equals("") ) {
                       Toast.makeText(MainActivity.this, "Поле не должно быто пустым", Toast.LENGTH_LONG).show();
                   }
                   else{
                       // Создадим файл и откроем поток для записи данных
                       FileOutputStream fileOutputStreamLogin = null;
                       FileOutputStream fileOutputStreamPassw = null;

                       try {
                           fileOutputStreamLogin = openFileOutput(fileLogin, MODE_PRIVATE);
                           fileOutputStreamPassw = openFileOutput(filePssw, MODE_PRIVATE);

                       } catch (FileNotFoundException e) {
                           e.printStackTrace();
                       }
                       // Обеспечим переход символьных потока данных к байтовым потокам.
                       OutputStreamWriter outputStreamWriterLogin = new OutputStreamWriter(fileOutputStreamLogin);
                       OutputStreamWriter outputStreamWriterPassw = new OutputStreamWriter(fileOutputStreamPassw);

                       // Запишем текст в поток вывода данных, буферизуя символы так, чтобы обеспечить эффективную запись отдельных символов.
                       BufferedWriter bwlogin = new BufferedWriter(outputStreamWriterLogin);
                       BufferedWriter bwpassw = new BufferedWriter(outputStreamWriterPassw);

                       // Осуществим запись данных
                       try {
                           String tmp = mLoginEdTxt.getText().toString();
                           bwlogin.write(mLoginEdTxt.getText().toString());
                           tmp = mPasswrdEdTxt.getText().toString();
                           bwpassw.write(mPasswrdEdTxt.getText().toString());

                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                       // закроем поток
                       try {
                           bwlogin.close();
                           bwpassw.close();

                       } catch (IOException e) {
                           e.printStackTrace();
                       }



                   }
               }
           }
        );

    }





}
