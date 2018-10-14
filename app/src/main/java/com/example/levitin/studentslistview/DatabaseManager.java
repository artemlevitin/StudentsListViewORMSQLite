package com.example.levitin.studentslistview;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

public class DatabaseManager {
    private static DatabaseManager instance;
    private DatabaseHelper helper;

    public DatabaseManager(){}

    public static DatabaseManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseManager.class) {
                if (instance == null) {
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }

    public void init(Context context){
        if(helper == null)
            helper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }

    public void release(){
        if(helper != null)
            OpenHelperManager.releaseHelper();
    }

    public DatabaseHelper getHelper() {
        return helper;
    }
}
