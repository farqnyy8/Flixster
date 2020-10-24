package com.vogella.android.rxjava.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    final public static String POSTERPATH = "poster_path";
    final public static String TITLE = "title";
    final public static String OVERVIEW = "overview";
    final public static String BACKDROPPATH = "backdrop_path";
    final public static String VOTEAVERAGE = "vote_average";

    String posterPath;
    String backdropPath;
    String title;
    String overView;
    double voteaverage;

    public Movie(JSONObject jsonObject) throws JSONException {
        backdropPath = jsonObject.getString(BACKDROPPATH);
        posterPath = jsonObject.getString(POSTERPATH);
        title = jsonObject.getString(TITLE);
        overView = jsonObject.getString(OVERVIEW);
        voteaverage = jsonObject.getDouble(VOTEAVERAGE);
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < movieJsonArray.length(); i++)
        {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getBackdroppath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }
    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public double getVoteaverage() {
        return voteaverage;
    }

    public String getTitle() {
        return title;
    }

    public String getOverView() {
        return overView;
    }
}
