package com.example.android.mvptest.Retrofit;

import com.example.android.mvptest.Repository.RetrofitNetworkApi;

public class ApiService {
    private static final String BASE_URL = "https://api.themoviedb.org/";

    private ApiService(){}

    public static RetrofitNetworkApi getApiService() {
        return RetrofitClient.getClient(BASE_URL).create(RetrofitNetworkApi.class);
    }
}
