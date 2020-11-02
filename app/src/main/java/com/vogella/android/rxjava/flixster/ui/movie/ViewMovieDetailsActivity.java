package com.vogella.android.rxjava.flixster.ui.movie;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.vogella.android.rxjava.flixster.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class ViewMovieDetailsActivity extends YouTubeBaseActivity {

    public static final String YOUTUBE_API_KEY = "AIzaSyC3xppbRiQR_ix7_aotqzpvaXZV8NfNCgA";
    public static final String VIDOES_URL = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US";
    public static final String TAG = "YouTubeClient";

    private static final String RESULTS = "results";
    private static final String KEY = "key";


    //TODO: add relase_date, and others
    TextView title;
    TextView overview;
    RatingBar ratingBar;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_movie_details);

        initUI();

        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra(Movie.MOVIE_AS_STRING));
        initUIData(movie);
    }

    private void initUIData(final Movie movie) {
        title.setText(movie.getTitle());
        overview.setText(movie.getOverView());
        ratingBar.setRating((float) movie.getRating());

        // Todo: remove network calls from View
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(String.format(VIDOES_URL, movie.getMovieId()), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                try {
                    JSONArray results = json.jsonObject.getJSONArray(RESULTS);
                    if(results.length() == 0) {
                        return;
                        // TODO: show some default image or video, and make sure that the site is YouTube
                    }
                    String youtubeKey = results.getJSONObject(0).getString(KEY);
                    // can't set movie key because of multithreading that is done here
                    initYouTube(youtubeKey);
                    Log.e(TAG, youtubeKey);
                } catch (JSONException e) {
                    Log.e(TAG, "Failed to parse JSON");
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.e(TAG, "Failed to get movie YouTube key");
            }
        });
    }

    private void initUI() {
        title = findViewById(R.id.tvViewMovieTitle);
        overview = findViewById(R.id.tvViewMovieOverview);
        ratingBar = findViewById(R.id.viewMovieRatingBar);
        youTubePlayerView = findViewById(R.id.player);
    }

    public void initYouTube(final String movieYoutubeKey) {
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d("ViewMovieDetailsAct", "onSuccess");
                youTubePlayer.cueVideo(movieYoutubeKey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult
                    youTubeInitializationResult) {
                Log.d("ViewMovieDetailsAct", "onFailure");
            }
        });
    }
}