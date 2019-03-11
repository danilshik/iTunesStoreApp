package com.example.itunesapp;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.itunesapp.database.Database;
import com.facebook.drawee.backends.pipeline.Fresco;

public class AppDelegate extends Application {

    private Database mDatabase;
    @Override
    public void onCreate() {
        super.onCreate();

        mDatabase = Room.
                databaseBuilder(this, Database.class, "database").
                build();

        Fresco.initialize(this);
    }

    public Database getDatabase() {
        return mDatabase;
    }
}
