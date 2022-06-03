package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;
import org.w3c.dom.Text;

public class MovieDetailsActivity extends AppCompatActivity {
        // the movie to display
        Movie movie;

        // the view objects
        TextView tvTitleDetail;
        TextView tvOverviewDetail;
        RatingBar rbVoteAverage;
        ImageView ivBigPoster;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_movie_details);

            tvTitleDetail = (TextView) findViewById(R.id.tvTitleDetail);
            tvOverviewDetail = (TextView) findViewById(R.id.tvOverviewDetail);
            rbVoteAverage = (RatingBar) findViewById(R.id.ratingBar);
            ivBigPoster = (ImageView) findViewById(R.id.ivBigPoster);

            // unwrap the movie passed in via intent, using its simple name as a key
            movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
            Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

            // set the title and overview
            tvTitleDetail.setText(movie.getTitle());
            tvOverviewDetail.setText(movie.getOverview());

            float voteAverage = movie.getVoteAverage().floatValue();
            rbVoteAverage.setRating(voteAverage / 2.0f);

            Glide.with(this).load(movie.getBackdropPath()).placeholder(R.drawable.flicks_backdrop_placeholder).into(ivBigPoster);
        }
}