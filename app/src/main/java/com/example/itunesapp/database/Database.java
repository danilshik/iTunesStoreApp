package com.example.itunesapp.database;


import android.arch.persistence.room.RoomDatabase;

@android.arch.persistence.room.Database(entities = {DBResult.class}, version = 1)
public abstract class Database extends RoomDatabase {

    abstract DBDao getDBDao();

}
