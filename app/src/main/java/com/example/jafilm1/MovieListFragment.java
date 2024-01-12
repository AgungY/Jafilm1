package com.example.jafilm1;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListFragment extends Fragment implements MovieAdapter.OnItemClickListener {
    private RecyclerView RecyclerView;
    private MovieAdapter MovieAdapter;

    public MovieListFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        RecyclerView = view.findViewById(R.id.movieRecyclerView);
        RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MovieAdapter = new MovieAdapter();
        MovieAdapter.setOnItemClickListener(this);
        RecyclerView.setAdapter(MovieAdapter);


        fetchPopularMovies();

        return view;
    }

    private void fetchPopularMovies() {

    }

    @Override
    public void onItemClick(Movie movie) {

    }
}

