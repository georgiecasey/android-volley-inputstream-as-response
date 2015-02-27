package com.georgiecasey.inputstreamresponseexampleusage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.InputStreamRequest;
import com.android.volley.toolbox.Volley;

public class ExampleUsage extends Activity {
	private static RequestQueue requestQueue;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestQueue = Volley.newRequestQueue(this);
		
		getResponseAsInputStream();
	}
	
    public void getResponseAsInputStream() {
    	// make a request with our new class to get an image
        InputStreamRequest photoRequest = new InputStreamRequest(Request.Method.GET, "http://httpbin.org/image", new Response.Listener<byte[]>() { 
            @Override
            public void onResponse(byte[] data) {
            	// we convert the byte array into our InputStream, what we need
                InputStream is = new ByteArrayInputStream(data);
                // i write the InputStream image to a file in the cache dir, just to see that everything works. 
                try {
                    final File file = new File(ExampleUsage.this.getCacheDir(), "test_image.jpg");
                    final OutputStream output = new FileOutputStream(file);
                    try {
                        try {
                            final byte[] buffer = new byte[1024];
                            int read;

                            while ((read = is.read(buffer)) != -1)
                                output.write(buffer, 0, read);

                            output.flush();
                        } finally {
                            output.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                     e.printStackTrace();
                } 
            } }, 
            new Response.ErrorListener() 
            {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("VOLLEYERROR","error => "+error.toString());
                }
            }
                ) {
        	// httpbin.org needs proper accept headers
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError { 
                    Map<String, String>  params = new HashMap<String, String>();  
                    params.put("Accept", "image/jpeg");

                    return params;  
            }
            
        };    
        requestQueue.add(photoRequest);
    }

}
