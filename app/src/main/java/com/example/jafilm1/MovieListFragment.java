package com.example.jafilm1;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListFragment extends Fragment implements MovieAdapter.OnItemClickListener {
    private RecyclerView RecyclerView;
    private MovieAdapter MovieAdapter;
    private List<Movie> list = new ArrayList<>();

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

        TmdbApiService api = TmdbApiManager.getApiService();

        Call<MovieResponse> call = api.getPopularMovies(TmdbApiManager.API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                MovieResponse res = response.body();
                list = res.results;

                MovieAdapter = new MovieAdapter(list, getContext(), MovieListFragment.this);
                LinearLayoutManager layout = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);

                RecyclerView.setLayoutManager(layout);
                RecyclerView.setAdapter(MovieAdapter);
                MovieAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {

            }
        });

        fetchPopularMovies();

        return view;
    }

    private void fetchPopularMovies() {

    }

    @Override
    public void onItemClick(Movie movie) {
        Log.d("onItemClick: ", movie.original_title);
    }
}

