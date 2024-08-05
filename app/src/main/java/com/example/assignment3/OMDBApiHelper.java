package com.example.assignment3;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class OMDBApiHelper {
    private static final String API_KEY = "f7afabb3";
    private static final String BASE_URL = "http://www.omdbapi.com/";

    public static void getMovies(String query, final MovieCallback callback) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = BASE_URL + "?apikey=" + API_KEY + "&s=" + query;
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.has("Search")) {
                        JSONArray moviesJsonArray = response.getJSONArray("Search");
                        List<Movie> movies = new ArrayList<>();
                        for (int i = 0; i < moviesJsonArray.length(); i++) {
                            JSONObject movieJson = moviesJsonArray.getJSONObject(i);
                            Movie movie = new Movie(
                                    movieJson.getString("Title"),
                                    movieJson.getString("Year"),
                                    movieJson.getString("imdbID"),
                                    movieJson.getString("Poster"),
                                    movieJson.getString("Type")
                            );
                            movie.setRating(movieJson.optString("imdbRating", "N/A"));
                            movie.setStudio(movieJson.optString("Production", "N/A"));
                            movies.add(movie);
                        }
                        callback.onSuccess(movies);
                    } else {
                        callback.onFailure("No movies found.");
                    }
                } catch (Exception e) {
                    Log.e("OMDBApiHelper", "Error parsing movie data", e);
                    callback.onFailure(e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                callback.onFailure(throwable.getMessage());
            }
        });
    }

    public interface MovieCallback {
        void onSuccess(List<Movie> movies);
        void onFailure(String errorMessage);
    }
}
