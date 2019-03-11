package com.csofcs.ehsanhasin.thebestcleaner.Model;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by King on 3/25/2018.
 */

public class LocalData {
    private static final String APP_SHARED_PREFS = "RemindMePref";

    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;


    private static final String min = "min";


    Context context;

    public LocalData(Context context) {
        this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
        this.context = context;
    }

    // Settings Page Reminder Time (Hour)


    public void reset() {
        prefsEditor.remove("user");
        prefsEditor.commit();

    }

    public void set_user_on(boolean v) {
        prefsEditor.putBoolean("isOn", v);
        prefsEditor.commit();

    }

    public boolean is_user_on() {
        return appSharedPrefs.getBoolean("isOn", false);
    }

    public void is_varifi_set(boolean v) {
        prefsEditor.putBoolean("va", v);
        prefsEditor.commit();

    }

    public boolean is_varifi() {
        return appSharedPrefs.getBoolean("va", false);
    }

    //   get  last date
    public void set_Last_Date(String v) {
        prefsEditor.putString("date", v);
        prefsEditor.commit();
    }
//   get  last date
    public String get_last_date() {
        return appSharedPrefs.getString("date", "2019-01-13 07:40:19");
    }
}
