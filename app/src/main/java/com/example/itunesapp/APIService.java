package com.example.itunesapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("search")
    Call<Response> getResponse(@Query("term") String term);
}
