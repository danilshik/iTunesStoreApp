package com.example.itunesapp.database;




import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.itunesapp.result.Result;

import java.util.List;


@Dao
public interface DBDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertResult(List<DBResult> results);

    @Query("select * from result")
    List<DBResult> getResult();

}
