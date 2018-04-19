package anilkaynar.com.anilspy2;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class CallService extends Service {
    CallReceiver receiver=new CallReceiver();
    CallReceiver receiver2=new CallReceiver();
    public CallService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        registerReceiver(receiver,new IntentFilter("android.intent.action.NEW_OUTGOING_CALL"));
        registerReceiver(receiver2,new IntentFilter("android.intent.action.NEW_INCOMING_CALL"));
        return START_STICKY;
    }
}
