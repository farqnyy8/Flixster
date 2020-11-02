package com.vogella.android.rxjava.flixster.client;

import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.vogella.android.rxjava.flixster.ui.movie.Movie;
import com.vogella.android.rxjava.flixster.ui.movie.MovieAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Headers;

public class MovieDbClient {

    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=";

    public static void loadNowPlayingMovies(final MovieAdapter adapter, final List<Movie> movies, final String tag)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(tag, "Nice and Dandy");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i(tag, "Results: " + results.toString());
                    movies.addAll(Movie.fromJsonArray(results));
                    adapter.notifyDataSetChanged();
                    Log.i(tag, "Movies: " + movies.size());
                } catch (JSONException e) {
                    Log.e(tag, "Hit Json Exception", e);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(tag, "Not Nice and Dandy");
            }
        });
    }
}
