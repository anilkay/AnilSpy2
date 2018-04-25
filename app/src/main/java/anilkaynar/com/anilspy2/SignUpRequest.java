package anilkaynar.com.anilspy2;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.HashMap;
import java.util.Map;

public class SignUpRequest extends Request<String> {
    private Map<String, String> mParams;
    public SignUpRequest(String url, Response.ErrorListener listener) {
        super(url, listener);
    }

    public SignUpRequest(int method, String url,String email,String userName,String password, Response.ErrorListener listener) {
        super(method, url, listener);
        mParams=new HashMap<>();
        mParams.put("username",userName);
        mParams.put("email",email);
        mParams.put("password",password);
    }




    @Override
    protected void deliverResponse(String response) {
        Log.e("Response",response);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {

        return mParams;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String data=new String(response.data);
        //Şuan response'u düzgün bir biçimde atabiliyoruz.
        return Response.success(data+" "+response.statusCode,
                HttpHeaderParser.parseCacheHeaders(response));
    }
}
