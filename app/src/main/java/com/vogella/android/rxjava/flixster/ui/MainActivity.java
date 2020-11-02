package com.vogella.android.rxjava.flixster.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vogella.android.rxjava.flixster.R;
import com.vogella.android.rxjava.flixster.client.MovieDbClient;
import com.vogella.android.rxjava.flixster.ui.movie.Movie;
import com.vogella.android.rxjava.flixster.ui.movie.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private static final String TAG = "MainActivity";

    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        movies = new ArrayList<>();

        // create adapter
        MovieAdapter adapter = new MovieAdapter(movies,this);

        // set adapter to rv
        rvMovies.setAdapter(adapter);

        // set a layout manager to rv
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        // get the data using the Api
        MovieDbClient.loadMovies(adapter, movies, NOW_PLAYING_URL, TAG);
    }
}