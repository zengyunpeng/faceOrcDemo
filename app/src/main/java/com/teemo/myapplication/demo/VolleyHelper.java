package com.teemo.myapplication.demo;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class VolleyHelper {
    private static VolleyHelper mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private VolleyHelper(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized VolleyHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyHelper(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            if (mCtx==null){
                return null;
            }
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> boolean addToRequestQueue(Request<T> req) {
        if (getRequestQueue()==null){
            return false;
        }
        req.setRetryPolicy(new DefaultRetryPolicy(10000, 0, 1.0f));
        getRequestQueue().add(req);
        return true;
    }

    public void clearRequestQueue(){
        if (getRequestQueue()!=null){
            getRequestQueue().cancelAll(mCtx.getApplicationContext());
        }
    }

}