package com.stac2021.mwproject;

import android.app.Application;
import android.util.Log;

public class app extends Application {
    private static String userId;
    public static String userName = null;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static String getUserName(){
        if (userName == null) return "회원";
        return userName;
    }

    public static String getUserId(){
        return userId;
    }
    public static String setUserId(String id){
        return userId = id;
    }
    public static String setUserName(String name){
        return userName = name;
    }
}