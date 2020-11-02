package com.vogella.android.rxjava.flixster.ui.movie;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {

    final public static String POSTER_PATH = "poster_path";
    final public static String TITLE = "title";
    final public static String OVERVIEW = "overview";
    final public static String BACKDROP_PATH = "backdrop_path";
    final public static String VOTE_AVERAGE = "vote_average";
    final public static String MOVIE_AS_STRING = "movie";
    final public static String ID = "id";

    private int movieId;
    private String posterPath;
    private String backdropPath;
    private String title;
    private String overView;
    private double rating;

    // empty constructor for parceler
    public Movie() {}

    public Movie(JSONObject jsonObject) throws JSONException {
        backdropPath = jsonObject.getString(BACKDROP_PATH);
        posterPath = jsonObject.getString(POSTER_PATH);
        title = jsonObject.getString(TITLE);
        overView = jsonObject.getString(OVERVIEW);
        rating = jsonObject.getDouble(VOTE_AVERAGE);
        movieId = jsonObject.getInt(ID);
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

    public double getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public String getOverView() {
        return overView;
    }

    public int getMovieId() {
        return movieId;
    }
}
