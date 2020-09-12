package com.solovyevs.memonotes;


import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import static com.solovyevs.memonotes.Constants.*;

public class PincodeEditor extends AppCompatActivity {
    private ToggleButton tggButton;
    private ToggleButton tggButtonCurrPass;
    private TextView password;
    private TextView oldpinET;
    private boolean checkOldPin = false;
    private String pinCode;
    private String checkPinCode;
    private String savedPin;
    private boolean focus;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pincode_editor);
        init();

    }


    public void init()
    {
        pinCode = EMPTY;
        checkPinCode = EMPTY;
        savedPin = EMPTY;
        focus = false;
        savedPin = App.getKeystore().getPincode(this);
        password = findViewById(R.id.passedit);
        TextView title = findViewById(R.id.textview_titleeditpass);
        TextView oldpinTV = findViewById(R.id.textview_oldpass);
        oldpinET = findViewById(R.id.oldpass);
        TextView newpassTV = findViewById(R.id.textview_newpass);
        ImageView backBtn = findViewById(R.id.backBtn);

        if(savedPin.equals(EMPTY))
        {
            title.setText(R.string.createpin);
            oldpinTV.setVisibility(View.GONE);
            oldpinET.setVisibility(View.GONE);
            newpassTV.setVisibility(View.GONE);
            focus = true;
            backBtn.setVisibility(View.GONE);

        }


        tggButtonCurrPass = findViewById(R.id. btnaccviewcurrpass);
        tggButtonCurrPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tggButtonCurrPass = (ToggleButton) v;
                if(!oldpinET.getText().toString().equals("")) {
                    if (!tggButtonCurrPass.isChecked()) {
                        oldpinET.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        oldpinET.setTextSize(50);
                    } else {
                        oldpinET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        oldpinET.setTextSize(30);
                    }
                }
            }
        });

        tggButton = findViewById(R.id. newpass);
        tggButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tggButton = (ToggleButton) v;
                if(!password.getText().toString().equals("")) {
                    if (!tggButton.isChecked()) {
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        password.setTextSize(50);
                    } else {
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        password.setTextSize(30);
                    }
                }
            }
        });

    }

    public void onClickGotoMainActivity(View view) {
        if (password.getText().toString().length() < 4){
            Toast.makeText(PincodeEditor.this, R.string.str_restrict, Toast.LENGTH_LONG).show();
            return;
        }

        if(!savedPin.equals(EMPTY) ) {
            if (!checkOldPin) {
                Toast.makeText(PincodeEditor.this, R.string.diff_pin, Toast.LENGTH_LONG).show();
                return;

            } else {
                Toast.makeText(PincodeEditor.this, R.string.change_pin, Toast.LENGTH_LONG).show();

            }
        }
        else {
            Toast.makeText(PincodeEditor.this, R.string.created_pin, Toast.LENGTH_LONG).show();
        }

        App.getKeystore().setPincode(this, password.getText().toString());
        Intent intent = new Intent(PincodeEditor.this, MainActivity.class);
        startActivity(intent);
        finish();
    }



    public void onKeyPrerssed(View view) {
        Button btnKey = (Button) view;
        String key = btnKey.getText().toString();
        String backKey = getString(R.string.backarrow);

        if(!checkPinCode.equals(savedPin))
        {
            oldpinET.setTextColor(getResources().getColor(R.color.TextRed));
        }

        if(!focus)
        {
            if (checkPinCode.length() < 4 && !key.equals(backKey)) {
                checkPinCode = checkPinCode + key;
                oldpinET.setText(checkPinCode);
                checkOldPin = false;

            }
            if(checkPinCode.length()>0 && key.equals(backKey)) {
                checkPinCode = checkPinCode.substring(0, checkPinCode.length() - 1);
                oldpinET.setText(checkPinCode);
                checkOldPin = false;
            }

            if (checkPinCode.length() == 4 && checkPinCode.equals(savedPin)) {
                if (!checkOldPin) {
                    checkOldPin = true;
                    oldpinET.setTextColor(getResources().getColor(R.color.TextGreen));
                    Toast.makeText(PincodeEditor.this, R.string.check_pin, Toast.LENGTH_LONG).show();
                    return;
                }

            }
            if (checkPinCode.length() == 4 && !checkPinCode.equals(savedPin)) {
                if (!checkOldPin) {
                    checkOldPin = false;
                    oldpinET.setTextColor(getResources().getColor(R.color.TextRed));
                    Toast.makeText(PincodeEditor.this, R.string.diff_pin, Toast.LENGTH_LONG).show();
                    return;
                }

            }

        }
        else
        {
            if(pinCode.length() < 4 ) {
                if (!key.equals(backKey)) {
                    pinCode = pinCode + key;
                }
            }

            if(pinCode.length()>0 && key.equals(backKey)) {
                pinCode = pinCode.substring(0, pinCode.length() - 1);
            }
            password.setText(pinCode);

        }


    }

    public void onClickFocusToOldPassedit(View view) {
        focus = false;
    }

    public void onClickFocusToPassedit(View view) {
        focus = true;
    }


    public void onClickBack(View view) {

            Intent intent = new Intent(PincodeEditor.this, MainActivity.class);
            startActivity(intent);
            finish();

    }
}
