package com.if5a.notifikasiku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import com.if5a.notifikasiku.databinding.ActivityMainBinding;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int notifId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //tambahkan StrictMode jika force close
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        binding.btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });

    }
        private void showNotification() {
            PendingIntent pendingIntent;
            Intent intent = new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            int pendingIntentFlag = PendingIntent.FLAG_UPDATE_CURRENT;

            pendingIntent = PendingIntent.getActivity(this,0, intent, pendingIntentFlag);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,"notifikasiku_default")
                    .setContentTitle("Panggilan Terakhir Reminder Tugas")
                    .setContentText("Panggilan Terakhir Reminder Tugas PAB2")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
//                    .setStyle(new NotificationCompat.BigTextStyle()
//                            .setBigContentTitle("Panggilan Terakhir Reminder Tugas")
//                            .bigText("Panggilan Terakhir Reminder Tugas PAB2. Pastikan jawaban yang dikumpul sesuai dengan intruksi!")
//                            .setSummaryText("Panggilan Terakhir Reminder Tugas"))
                    .setContentInfo("Notifikasiku")
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                    .setColor(ContextCompat.getColor(this, android.R.color.transparent))
                    .setLights(Color.RED, 1000,300)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setNumber(3)
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24);

            String picture = "https://static.vecteezy.com/system/resources/thumbnails/004/581/274/small/bell-alert-notification-icon-set-free-vector.jpg";

            try {
                if (picture != null && !picture.equals("")) {
                    URL url = new URL(picture);
                    Bitmap bigPicture = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    notificationBuilder.setStyle(new NotificationCompat.BigPictureStyle()
                            .bigPicture(bigPicture)
                            .setBigContentTitle("Panggilan Terakhir Reminder Tugas")
                            .setSummaryText("Panggilan Terakhir Reminder Tugas PAB2"));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                pendingIntentFlag = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE;
            }

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                NotificationChannel channel = new NotificationChannel("notifikasiku_default","notifikasiku", NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription("");
                channel.setShowBadge(true);
                channel.canShowBadge();
                channel.enableLights(true);
                channel.setLightColor(Color.RED);
                channel.enableVibration(true);
                channel.setVibrationPattern(new long[] {100,200,300,400,500});

                if(notificationManager != null){
                    notificationManager.createNotificationChannel(channel);
                }
            }

            if(notificationManager != null){
                notificationManager.notify(notifId++,notificationBuilder.build());
            }
        }


}