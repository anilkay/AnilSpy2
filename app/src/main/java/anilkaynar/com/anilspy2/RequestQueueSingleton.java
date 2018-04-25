package anilkaynar.com.anilspy2;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestQueueSingleton {
    static private RequestQueue  requestQueue;

    public static RequestQueue getRequestQueue(Context context) {
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(context);
            return requestQueue;
        }
        else {
            return requestQueue;
        }
    }
}
