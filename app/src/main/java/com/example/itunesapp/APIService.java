package com.example.itunesapp;

import com.example.itunesapp.data.Response;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("search")
    Single<Response> getResponse(@Query("term") String term);
}
