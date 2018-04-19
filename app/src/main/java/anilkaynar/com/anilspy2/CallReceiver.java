package anilkaynar.com.anilspy2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class CallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //
        RequestQueue requestQueue=Volley.newRequestQueue(context);
        String savedNumber="";
        String savedNumber2= intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            savedNumber = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
        }
        Log.e("Saved number","Numara: "+savedNumber);
        Log.e("İt is ","İt is it");
        Log.e("Saved number 2 ",savedNumber2);
        StringRequest request=new StringRequest(Request.Method.GET,"https://google.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("REsopnes"," ey"+response);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
        //Volley de düzgün bir biçimde çalışıyor.
        // an Intent broadcast.

    }
}

/*
   String savedNumber="";
            String savedNumber2= intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            savedNumber = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");

        Log.e("Saved number","Numara: "+savedNumber);
        Log.e("İt is ","İt is it");
        Log.e("Saved number 2 ",savedNumber2);
 */