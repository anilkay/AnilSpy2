package anilkaynar.com.anilspy2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        startActivity(intent);
        Intent notificationServiceIntent=new Intent(MainActivity.this,
                      NotificationService.class);
        MainActivity.this.startService(notificationServiceIntent);
    }
}
