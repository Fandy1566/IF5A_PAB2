package com.if5a.kamus.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.if5a.kamus.R;

public class AppPreference {
    private SharedPreferences prefs;
    private Context context;

    public AppPreference(Context context){
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void setFirstRun(Boolean input) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(context.getString(R.string.app_first_run),input);
        editor.commit();
    }

    public Boolean getFirstRun() {
        return prefs.getBoolean(context.getString(R.string.app_first_run),true);
    }
}
