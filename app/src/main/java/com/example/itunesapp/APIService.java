package com.example.itunesapp;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.internal.operators.completable.CompletableAmb;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @GET("search")
    Single<Response> getResponse(@Query("term") String term);
}
