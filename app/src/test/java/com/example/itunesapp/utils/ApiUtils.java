package com.example.itunesapp.utils;

import com.example.itunesapp.APIService;
import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {
    private static String apiUrl = "https://itunes.apple.com/";
    private static Retrofit retrofit;
    private static APIService api;

    public static Retrofit getRetrofit(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(apiUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return retrofit;
    }


    public static APIService getApi(){
        if(api == null){
            api = getRetrofit().create(APIService.class);
        }
        return api;
    }
}
