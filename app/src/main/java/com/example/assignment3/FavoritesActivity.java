package com.example.assignment3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        recyclerView = findViewById(R.id.recycler_view_favorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);
        loadFavorites();
    }

    private void loadFavorites() {
        List<Movie> favoriteMovies = databaseHelper.getFavorites();
        movieAdapter = new MovieAdapter(favoriteMovies, new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                // Handle item click
            }
        });
        recyclerView.setAdapter(movieAdapter);
    }
}
