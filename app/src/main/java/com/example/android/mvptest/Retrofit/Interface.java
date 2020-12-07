package com.example.android.mvptest.Retrofit;


import com.example.android.mvptest.Model.Second;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Interface {

    @GET("3/search/movie")
    Call<Second> getQueryList(
            @Query("api_key") String api_key,
            @Query("query") String query

    );


}
