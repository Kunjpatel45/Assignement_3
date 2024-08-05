package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText searchField;
    private Button searchButton;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.search_field);
        searchButton = findViewById(R.id.search_button);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchField.getText().toString().trim();
                if (!query.isEmpty()) {
                    fetchMovies(query);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a movie name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fetchMovies(String query) {
        OMDBApiHelper.getMovies(query, new OMDBApiHelper.MovieCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                movieAdapter = new MovieAdapter(movies, new MovieAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Movie movie) {
                        Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                        intent.putExtra("MOVIE", movie);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(MainActivity.this, "Error fetching movies: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
