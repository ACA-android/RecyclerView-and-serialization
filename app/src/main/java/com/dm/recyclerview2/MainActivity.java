package com.dm.recyclerview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movieList;

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gson = new Gson();

        movieList = new ArrayList<>();
        movieList.add(createMovie());
        movieList.add(createMovie());
        movieList.add(createMovie());
        movieList.add(createMovie());
        movieList.add(createMovie());

        RecyclerView movieRecyclerView = findViewById(R.id.movieListView);

        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        MovieAdapter adapter = new MovieAdapter(movieList);
        adapter.setListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                openMovieDetailsPage(movie);
            }

            @Override
            public void onItemLongClickListener(Movie movie) {

            }
        });
        movieRecyclerView.setAdapter(adapter);
    }

    private void openMovieDetailsPage(Movie movie) {
        String movieJson = gson.toJson(movie);

        Intent intent = new Intent(MainActivity.this, MovieActivity.class);
        intent.putExtra("movie", movie);
        intent.putExtra("movieJson", movieJson);
        startActivity(intent);
    }


    private Movie createMovie() {
        List<String> actors = new ArrayList<>();
        actors.add("Sam");
        actors.add("Zoe");
        actors.add("Steven");

        Bitmap coverImage = BitmapFactory.decodeResource(getResources(), R.drawable.avatar);

        return new Movie.Builder("Avatar")
                .setDuration(162)
                .setYear(2009)
                .setDirector("James Cameron")
                .setActors(actors)
                .setCoverImage(coverImage)
                .create();
    }
}