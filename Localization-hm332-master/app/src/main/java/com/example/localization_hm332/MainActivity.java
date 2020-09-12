package com.example.localization_hm332;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Spinner mLocalisation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocalisation = findViewById(R.id.language);
        chooseLanguage();
    }
    private void chooseLanguage() {

        ArrayAdapter<CharSequence> adapterCountries = ArrayAdapter.createFromResource(this, R.array.language,
                android.R.layout.simple_spinner_item);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLocalisation.setAdapter(adapterCountries);
    }

    public void onClick(View view) {
        String localeStr = mLocalisation.getSelectedItem().toString();
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
        recreate();
    }
}
