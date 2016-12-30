package com.example.jeonjin_il.mysecondapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyService extends Service {
    private DBHelper db;
    private Notification.Builder builder;
    private NotificationManager nm;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String formatDate = new SimpleDateFormat("yyyy/MM/dd").format(date);


        Intent intent1 = new Intent(this, MainActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent1, 0);
        builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.main);
        builder.setTicker("물드세요");
        builder.setWhen(System.currentTimeMillis());
        builder.setContentTitle("물이 건강에 중요합니다");
        builder.setContentText("기록하려면 클릭하세요");
        builder.setContentIntent(pendingIntent);

        db = new DBHelper(getApplication().getApplicationContext(), "WATER.db", null, 1);

        ArrayList<Integer> day_data = db.getTotal_History(formatDate);

        builder.setProgress(day_data.get(1), day_data.get(0), false);
        builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(123456, builder.build());




        return Service.START_STICKY;
    }
}
