package com.vogella.android.rxjava.flixster.service;

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

public class NetworkCallsHelper {

    public static void loadMovies(final MovieAdapter adapter, final List<Movie> movies, String url, final String tag)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
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
