package com.vogella.android.rxjava.flixster.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.vogella.android.rxjava.flixster.R;
import com.vogella.android.rxjava.flixster.ui.movie.ViewMoviesActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // navigate to the view movies screen
        startActivity(new Intent(this, ViewMoviesActivity.class));
    }
}