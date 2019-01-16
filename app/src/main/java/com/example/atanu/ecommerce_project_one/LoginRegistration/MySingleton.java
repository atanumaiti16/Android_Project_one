package com.example.atanu.ecommerce_project_one.LoginRegistration;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {


    private static  MySingleton mSingletonPattern;
    private  static RequestQueue requestQueue;
    private  static Context mContext;

    public MySingleton(Context context){
        mContext = context;
        requestQueue = getRequestQueue();

    }

    public RequestQueue getRequestQueue(){
        if (requestQueue ==null){
            requestQueue =Volley.newRequestQueue(mContext.getApplicationContext());
        }

        return requestQueue;
    }

    public  static  synchronized MySingleton getInstance(Context context){
        if(mSingletonPattern == null){
            mSingletonPattern = new MySingleton(context);
        }
        return  mSingletonPattern;
    }
    public<T>  void add2requestQueue(Request<T> request){
        requestQueue.add(request);

    }

}
