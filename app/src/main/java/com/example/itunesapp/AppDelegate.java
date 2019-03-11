package com.example.itunesapp;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

public class AppDelegate extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
    }
}
