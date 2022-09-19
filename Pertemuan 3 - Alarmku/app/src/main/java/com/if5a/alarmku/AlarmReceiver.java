package com.if5a.alarmku;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.text.CaseMap;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String TYPE_ONE_TIME = "OneTimeAlarm";
    public static final String TYPE_REPEATING = "RepeatingAlarm";
    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_TYPE = "text";

    public static final int ID_ONETIME = 100;
    public static final int ID_REPEATING = 101;

    public AlarmReceiver(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }

    private void ShowAlarmNotification(Context context, String title, String message, int notifId){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService()
    }
}
