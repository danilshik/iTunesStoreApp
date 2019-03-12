package com.example.itunesapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.itunesapp.ui.result.ResultFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, ResultFragment.newInstance())
                    .commit();
        }
    }
}
