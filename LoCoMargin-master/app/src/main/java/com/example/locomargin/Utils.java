package com.example.locomargin;

import android.app.Activity;
import android.content.Intent;

import com.example.locomargin.R;

public class Utils {

    private static int sTheme;

    public final static int THEME_MARGIN1 = 0;
    public final static int THEME_MARGIN2 = 1;
    public final static int THEME_MARGIN3 = 2;

    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
     */
    public static void changeToTheme(Activity activity, int theme)
    {
        sTheme = theme;
        activity.finish();

        activity.startActivity(new Intent(activity, activity.getClass()));

    }

    /** Set the theme of the activity, according to the configuration. */
    public static void onActivityCreateSetTheme(Activity activity)
    {
        switch (sTheme)
        {
            default:
            case THEME_MARGIN1:
                activity.setTheme(R.style.Margin1);
                break;
            case THEME_MARGIN2:
                activity.setTheme(R.style.Margin2);
                break;
            case THEME_MARGIN3:
                activity.setTheme(R.style.Margin3);
                break;
        }
    }
}
