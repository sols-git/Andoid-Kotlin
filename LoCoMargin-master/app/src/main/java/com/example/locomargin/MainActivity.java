package com.example.locomargin;


import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
 {
    private Spinner mLocalisation;
    private Spinner mColor;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);

        mLocalisation = findViewById(R.id.language);
        mColor = findViewById(R.id.color);
        chooseLanguage();
        chooseColor();
    }
    private void chooseLanguage() {

        ArrayAdapter<CharSequence> adapterCountries = ArrayAdapter.createFromResource(this, R.array.language,
                android.R.layout.simple_spinner_item);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLocalisation.setAdapter(adapterCountries);
    }

     private void chooseColor() {

         ArrayAdapter<CharSequence> adapterCountries = ArrayAdapter.createFromResource(this, R.array.spin_margin,
                 android.R.layout.simple_spinner_item);
         adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         mColor.setAdapter(adapterCountries);
     }

    public void onClick(View view) {
        String localeStr = mLocalisation.getSelectedItem().toString();
        String color = mColor.getSelectedItem().toString();

        Locale locale;
        if(localeStr.equals("Английский") || localeStr.equals("Anglais") ) {
            locale = new Locale("en");
        }
        else if (localeStr.equals("Французский") || localeStr.equals("French"))
        {
            locale = new Locale("fr");
        }
        else
        {
            locale = new Locale("ru");
        }
        Configuration config = new Configuration();
        config.setLocale(locale);

        getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        switch (color)
        {
            default:
            case "Мелкие":
            case "Small":
            case "Petit":
                Utils.changeToTheme(this, Utils.THEME_MARGIN1);
                break;
            case "Средние":
            case "Medium":
            case "Moyen":
                Utils.changeToTheme(this, Utils.THEME_MARGIN2);
                break;
            case "Большие":
            case "Large":
            case "Grand":
                Utils.changeToTheme(this, Utils.THEME_MARGIN3);
                break;
        }



    }
}
