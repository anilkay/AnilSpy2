package anilkaynar.com.anilspy2;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        startActivity(intent);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                ,Manifest.permission.ACCESS_COARSE_LOCATION
                },
                //Ne zaman bir location change algılansa çağırılıyor.

                4);
        Intent notificationServiceIntent=new Intent(MainActivity.this,
                      NotificationService.class);
        MainActivity.this.startService(notificationServiceIntent);
        Intent locationServiceIntent=new Intent(MainActivity
                .this,LocationReadService.class);
        MainActivity.this.startService(locationServiceIntent);
    }
}
