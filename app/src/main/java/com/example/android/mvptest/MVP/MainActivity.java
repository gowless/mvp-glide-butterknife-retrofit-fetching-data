package com.example.android.mvptest.MVP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.mvptest.Adapter.Adapter;
import com.example.android.mvptest.Adapter.AdapterAllMain;
import com.example.android.mvptest.Model.Result;
import com.example.android.mvptest.Model.ResultSecond;
import com.example.android.mvptest.Model.Second;
import com.example.android.mvptest.R;
import com.example.android.mvptest.Retrofit.Initializator;
import com.example.android.mvptest.Retrofit.Interface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  implements MainActivityContract.View{

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    MainActivityPresenter presenter;
    Adapter adapter;
    ArrayList<Result> list = new ArrayList<>();
    SearchView searchView;
    List<ResultSecond> listData;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        searchView = findViewById(R.id.searchView);
        unbinder = ButterKnife.bind(this);

        presenter = new MainActivityPresenter();
        presenter.attachView(this);
        presenter.start();
        presenter.loadMovies();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setHasFixedSize(true);

                recyclerView.setDrawingCacheEnabled(true);
                recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                getJsonData("8c6ec233255ed6fd9e815b94ff2d64e3", s);


                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }

    @Override
    public void setErrorUi(String error) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUi(ArrayList<Result> data) {
        progressBar.setVisibility(View.GONE);
        list.clear();
        list.addAll(data);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void initUi() {
        progressBar.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.hasFixedSize();
        adapter = new Adapter(this, list);
        recyclerView.setAdapter(adapter);
    }


    //setting to get json file and parse it to models
    public void getJsonData(String api_key, String query){

        Interface apiInterfaceCount = Initializator.getClient().create(Interface.class);
        Call<Second> call = apiInterfaceCount.getQueryList(api_key, query);
        progressBar.setIndeterminate(true);
        call.enqueue(new Callback<Second>() {
            @Override
            public void onResponse(@NonNull Call<Second> call, @NonNull Response<Second> response) {
                Log.d("TASG", response.body().getResults().get(1).getTitle());
                assert response.body() != null;
                listData = response.body().getResults();

                AdapterAllMain recyclerAdapterAllMain = new AdapterAllMain(getApplicationContext(), listData);
                recyclerAdapterAllMain.setDataList(listData);
                recyclerView.setAdapter(recyclerAdapterAllMain);

                progressBar.setIndeterminate(false);
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(@NonNull Call<Second> call, @NonNull Throwable t) {
                Log.d("TASG", t.toString());
            }
        });

    }




}
