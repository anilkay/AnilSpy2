package anilkaynar.com.anilspy2;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class NotificationService extends NotificationListenerService{
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        Log.e("Notification Service","Notifcation yakalandı "+sbn.getTag());
        Log.e("Evet evet yakalandı","Yakaladık kardeşim");
        Notification notification=sbn.getNotification();
        Log.e("ToString",notification.toString());
        //Çalışıyor dostum çalışıyor
       Bundle extras=notification.extras;
        String title = extras.getString("android.title");
        String text = extras.getCharSequence("android.text").toString();
        Log.e("Title",title); //Title: Selin
        Log.e("Text",text);   //2 yeni Mesaj
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.e("Notification is removed","Remove this fucking notification");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);

    }

    @Override
    public void onListenerConnected() {
        Log.e("Listener bağlandı","Bağlanndı");
        super.onListenerConnected();
    }

    @Override
    public void onListenerDisconnected() {
        Log.e("Listener ayrılrı,","Bağlanndı");

        super.onListenerDisconnected();
    }
}

