package com.example.Quran.thehollyquran;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

/**
 * Created by starapps on 1/24/2017.
 */
public class NotificationReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context,Quran.class);
        intent1.putExtra("page","293");
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,intent1,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context).
                setContentIntent(pendingIntent).
                setSmallIcon(android.R.drawable.ic_menu_agenda).
                setContentText("لا تنسى قراءة سورة الكهف").
                setContentTitle("تذكير يوم الجمعة").
        setAutoCancel(true);
        builder.setContentIntent(pendingIntent);
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setAutoCancel(true);

        notificationManager.notify(100,builder.build());

    }
}
