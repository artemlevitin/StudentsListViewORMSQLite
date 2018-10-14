package com.example.levitin.studentslistview;

import android.app.Application;
import android.util.Log;

public class MainApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("myLogs", "Application.onCreate");
        DatabaseManager.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate(){
        super.onTerminate();
        Log.d("myLogs", "Application.onTerminate");
    }
}
