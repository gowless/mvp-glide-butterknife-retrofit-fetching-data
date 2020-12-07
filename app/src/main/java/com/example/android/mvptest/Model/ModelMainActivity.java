package com.example.android.mvptest.Model;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.android.mvptest.MVP.MainActivityContract;
import com.example.android.mvptest.Retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelMainActivity implements MainActivityContract.Model {

    public static ArrayList<Result> dataList;
    MainActivityContract.Presenter presenter;

    public ModelMainActivity(MainActivityContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getMoviesList() {
        ApiService.getApiService().getMovies()
                .enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        if (response.isSuccessful()) {
                            presenter.onSuccess((ArrayList<Result>) response.body().getResults());
                        }
                        else {
                            assert response.errorBody() != null;
                            presenter.onError(response.errorBody().toString());
                            Log.i("mylog", "1 _" + response.errorBody().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        presenter.onError(t.getMessage());
                        Log.i("mylog", "2 _" + t.getMessage());
                    }
                });
    }



    }
