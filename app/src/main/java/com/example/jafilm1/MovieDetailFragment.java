package com.example.jafilm1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class MovieDetailFragment extends Fragment {
    private  String popularity, description, rating, releaseDate, title, image;
    private ImageView imageViewPoster;
    private TextView textViewTitle, textViewDescription, textViewRating, textViewReleaseDate, textViewPopularity;

    public MovieDetailFragment(String img, String title, String description, String rating, String releaseDate, String popularity) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.image = img;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        imageViewPoster = view.findViewById(R.id.imageViewDetailPoster);
        textViewTitle = view.findViewById(R.id.textViewDetailTitle);
        textViewDescription = view.findViewById(R.id.textViewDetailDescription);
        textViewRating = view.findViewById(R.id.textViewDetailRating);
        textViewReleaseDate = view.findViewById(R.id.textViewDetailReleaseDate);
        textViewPopularity = view.findViewById(R.id.textViewDetailPopularity);

        textViewTitle.setText(this.title);
        textViewDescription.setText(this.description);
        textViewRating.setText(this.rating);
        textViewReleaseDate.setText(this.releaseDate);
        textViewPopularity.setText(this.popularity);

        // Get movie data from arguments and display details
        Glide.with(getContext())
                .load("https://image.tmdb.org/t/p/w500" + this.image)
                .into(imageViewPoster);

        return view;
    }
}


