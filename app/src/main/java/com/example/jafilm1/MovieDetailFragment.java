package com.example.jafilm1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class MovieDetailFragment extends Fragment {
    private ImageView imageViewPoster;
    private TextView textViewTitle, textViewDescription, textViewRating, textViewReleaseDate, textViewPopularity;

    public MovieDetailFragment() {
        // Required empty public constructor
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

        // Get movie data from arguments and display details

        return view;
    }
}


