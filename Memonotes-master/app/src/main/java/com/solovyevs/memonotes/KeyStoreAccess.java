package com.solovyevs.memonotes;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class KeyStoreAccess implements KeyStoreAccessable {
    private String PIN_TEXT = "pincode";
    private SharedPreferences accessSharedPref;

    public KeyStoreAccess(Context context) {
        accessSharedPref = context.getSharedPreferences(PIN_TEXT, MODE_PRIVATE);
    }

    @Override
    public String getPincode(Context context) {
        String pincode = accessSharedPref.getString(PIN_TEXT, "");
        return pincode;
    }

    @Override
    public void setPincode(Context context, String pincode) {
        SharedPreferences.Editor myEditor = accessSharedPref.edit();
        myEditor.putString(PIN_TEXT, pincode);
        myEditor.apply();
    }

    @Override
    public boolean checkAccess(Context context, String pin) {
        boolean statusAccess = false;
        if(getPincode(context).equals(pin)){
            statusAccess = true;
        }

        return statusAccess;
    }
}
