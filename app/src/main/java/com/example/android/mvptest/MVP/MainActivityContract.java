package com.example.android.mvptest.MVP;

import com.example.android.mvptest.Model.Result;

import java.util.ArrayList;

public interface MainActivityContract {
    interface View{
        void setErrorUi(String error);

        void updateUi(ArrayList<Result> data);

        void initUi();
    }

    interface Presenter{
        void loadMovies();

        void onSuccess(ArrayList<Result> body);

        void onError(String error);

        void start();

        void attachView(MainActivityContract.View view);
        void detachView();
    }

    interface Model{
        void getMoviesList();
    }

    interface Models{
      void getMoviesList(String query);
    }


}
