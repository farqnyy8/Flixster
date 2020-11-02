package com.vogella.android.rxjava.flixster.ui.movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vogella.android.rxjava.flixster.R;
import com.vogella.android.rxjava.flixster.client.MovieDbClient;

import java.util.ArrayList;
import java.util.List;

public class ViewMoviesActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_movies);

        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        movies = new ArrayList<>();

        // create adapter
        MovieAdapter adapter = new MovieAdapter(movies,this);

        // set adapter to rv
        rvMovies.setAdapter(adapter);

        // set a layout manager to rv
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        // get the data using the Api
        MovieDbClient.loadNowPlayingMovies(adapter, movies, TAG);
    }
}