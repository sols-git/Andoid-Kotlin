package com.solovyevs.memonotes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import static com.solovyevs.memonotes.Constants.*;


public class AccessActivity extends AppCompatActivity  {

    private String pinCode;
    private String savedPin;
    private TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);
        init();

    }

    public void init()
    {

        pinCode = EMPTY;
        savedPin = App.getKeystore().getPincode(this);

        password = findViewById(R.id.pinaccessTv);

        if(savedPin.equals(EMPTY)){
            Intent intent = new Intent(AccessActivity.this, PincodeEditor.class);
            startActivity(intent);
            finish();

        }
    }

    public void onKeyPrerssed(View view) {

        Button btnKey = (Button) view;
        String key = btnKey.getText().toString();
        String backKey = getString(R.string.backarrow);

        TextView testErr = findViewById(R.id.texterr);
        testErr.setText(EMPTY);


        if (pinCode.length() < 4 && !key.equals(backKey)) {
                pinCode = pinCode + key;
                password.setText(pinCode);

            }
        if(pinCode.length()>0 && key.equals(backKey)) {
                pinCode = pinCode.substring(0, pinCode.length() - 1);
                password.setText(pinCode);
            }


        if (pinCode.length() == 4) {
            if (App.getKeystore().checkAccess(this, pinCode)) {
                Intent intent = new Intent(AccessActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
            testErr.setText(R.string.pinerror);
            pinCode = EMPTY;
            password.setText(pinCode);
            }

        }


    }


}
