package com.dm.recyclerview2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

public class MovieActivity extends AppCompatActivity {

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        gson = new Gson();

        Intent sourceIntent = getIntent();
        Movie movie = (Movie) sourceIntent.getParcelableExtra("movie");
        String movieJson = sourceIntent.getStringExtra("movieJson");
//        Movie movie = gson.fromJson(movieJson, Movie.class);

        if (movie != null) {
            ImageView coverImage = findViewById(R.id.coverImage);
            TextView title = findViewById(R.id.titleTextView);
            TextView score = findViewById(R.id.scoreTextView);

            coverImage.setImageBitmap(movie.getCoverImage());
            title.setText(getString(R.string.movie_title, movie.getTitle(), movie.getDirector()));
            score.setText(movie.getCriticScore() + "");

        }
    }
}