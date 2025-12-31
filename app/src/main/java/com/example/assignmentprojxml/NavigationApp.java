package com.example.assignmentprojxml;

import android.app.Application;
import com.example.assignmentprojxml.di.AppComponent;
import com.example.assignmentprojxml.di.DaggerAppComponent;

public class NavigationApp extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}