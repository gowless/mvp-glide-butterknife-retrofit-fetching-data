package com.example.android.mvptest.MVP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.mvptest.Model.Result;
import com.example.android.mvptest.Model.ResultSecond;
import com.example.android.mvptest.R;

public class DetailsActivity extends AppCompatActivity {
    TextView description, date, directors, cast, genres;
    ImageView poster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        description = findViewById(R.id.description);
        date = findViewById(R.id.date);
        directors = findViewById(R.id.directors);
        cast = findViewById(R.id.cast);
        genres = findViewById(R.id.genres);
        poster = findViewById(R.id.poster);



        Intent i = getIntent();
        if (i.getIntExtra("test", 0) == 1){
            ResultSecond resultSecond = (ResultSecond) i.getSerializableExtra("example");
            //setting image holder with glide
            Glide.with(this)
                    .load("http://image.tmdb.org/t/p/w500"+resultSecond.getPosterPath())
                    .centerInside()
                    .into(poster);

            description.setText(resultSecond.getOverview());
            date.setText(resultSecond.getReleaseDate());
            directors.setText(resultSecond.getAdult().toString());
            cast.setText(resultSecond.getOriginalTitle());
            genres.setText(resultSecond.getGenreIds().toString());

        } else {
            Result result = (Result) i.getSerializableExtra("example");
            //setting image holder with glide
            Glide.with(this)
                    .load("http://image.tmdb.org/t/p/w500"+result.getPosterPath())
                    .centerInside()
                    .into(poster);

            description.setText(result.getOverview());
            date.setText(result.getReleaseDate());
            directors.setText(result.getAdult().toString());
            cast.setText(result.getOriginalTitle());
            genres.setText(result.getGenreIds().toString());

        }



    }
}