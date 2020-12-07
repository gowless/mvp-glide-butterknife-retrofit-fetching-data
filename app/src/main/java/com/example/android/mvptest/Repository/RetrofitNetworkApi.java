package com.example.android.mvptest.Repository;

import com.example.android.mvptest.Model.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitNetworkApi {




    @GET("/3/trending/movie/week?api_key=8c6ec233255ed6fd9e815b94ff2d64e3")
    Call<Example> getMovies();
}
