package com.example.callsmsintent_hw722;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 11;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button callBtn = findViewById(R.id.callbtn);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callByNumber();

            }
        });

        Button sendSms = findViewById(R.id.smssend);
        sendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSms();
            }
        });
    }

    private void callByNumber() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {

            EditText phoneNumber = findViewById(R.id.tnumber);
            String tnum = "tel:" + phoneNumber.getText().toString();
            Intent actionCall = new Intent(Intent.ACTION_CALL, Uri.parse(tnum));
            startActivity(actionCall);
        }
    }

    private void sendSms(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
        } else {
            EditText phoneNumber = findViewById(R.id.tnumber);
            String tnum = phoneNumber.getText().toString();
            EditText message = findViewById(R.id.message);
            String messagestr = message.getText().toString();
            SmsManager smgr = SmsManager.getDefault();
            smgr.sendTextMessage(tnum, null, messagestr, null, null);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
// Проверяем результат запроса на право позвонить
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
// Разрешение получено, осуществляем звонок
                    callByNumber();
                } else {
// Разрешение не дано. Закрываем приложение
                    finish();
                }
            }
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
// Проверяем результат запроса на право позвонить
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
// Разрешение получено, осуществляем звонок
                    sendSms();
                } else {
// Разрешение не дано. Закрываем приложение
                    finish();
                }
            }
        }
    }
}
