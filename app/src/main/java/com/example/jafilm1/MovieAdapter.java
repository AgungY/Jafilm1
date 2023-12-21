package com.example.jafilm1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private final List<Movie> movies;
    private final Context context;

    public MovieAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.titleTextView.setText(movie.original_title);
        holder.overviewTextView.setText(movie.overview);

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + movie.poster_path)
                .into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImageView;
        TextView titleTextView;
        TextView overviewTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.moviePosterImageView);
            titleTextView = itemView.findViewById(R.id.movieTitleTextView);
            overviewTextView = itemView.findViewById(R.id.movieOverviewTextView);
        }
    }
}
