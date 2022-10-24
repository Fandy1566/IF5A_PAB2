package com.if5a.booksdictionary.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppPreference {
    private SharedPreferences prefs;
    private Context context;

    public AppPreference(Context context){
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }
}
