package com.solovyevs.memonotes;
import android.content.Context;


public interface KeyStoreAccessable {

    String getPincode(Context context);

    void setPincode(Context context, String pincode);

    boolean checkAccess(Context context, String pin);

}
