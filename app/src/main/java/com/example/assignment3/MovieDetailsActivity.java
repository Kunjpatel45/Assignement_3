package com.example.assignment3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView title, description, rating, year, studio;
    private ImageView poster;
    private Button favoriteButton;
    private DatabaseHelper databaseHelper;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        title = findViewById(R.id.movie_title);
        description = findViewById(R.id.movie_description);
        rating = findViewById(R.id.movie_rating);
        year = findViewById(R.id.movie_year);
        studio = findViewById(R.id.movie_studio);
        poster = findViewById(R.id.movie_poster);
        favoriteButton = findViewById(R.id.favorite_button);

        databaseHelper = new DatabaseHelper(this);

        movie = (Movie) getIntent().getSerializableExtra("MOVIE");

        if (movie != null) {
            displayMovieDetails();
        } else {
            Toast.makeText(this, "Error loading movie details", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if movie details are missing
        }

        favoriteButton.setOnClickListener(v -> {
            databaseHelper.addFavorite(movie);
            Toast.makeText(MovieDetailsActivity.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
        });
    }

    private void displayMovieDetails() {
        title.setText(movie.getTitle());
        description.setText(movie.getDescription());
        rating.setText("Rating: " + movie.getRating());
        year.setText("Year: " + movie.getYear());
        studio.setText("Studio: " + movie.getStudio());
        Picasso.get().load(movie.getPosterUrl()).into(poster);
    }
}
