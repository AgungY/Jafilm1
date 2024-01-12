package com.example.jafilm1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private FirebaseUser firebaseUser;
    private List<Movie> list = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        Toolbar toolbar = findViewById(R.id.my_toolbar);

        if(firebaseUser!=null){
            toolbar.setTitle("Hello, "+ firebaseUser.getDisplayName());
        }else{
            toolbar.setTitle("Login gagal!");
        }

        setSupportActionBar(toolbar);
        this.getMoviesData();
    }


    void getMoviesData() {
        TmdbApiService api = TmdbApiManager.getApiService();

        Call<MovieResponse> call = api.getPopularMovies(TmdbApiManager.API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                MovieResponse res = response.body();
                list = res.results;

                MovieAdapter adapter = new MovieAdapter(list, getApplicationContext());
                LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);

                RecyclerView recycle = findViewById(R.id.movieRecyclerView);
                recycle.setLayoutManager(layout);
                recycle.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {

            }
        });
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getOrder()) {
            case 0:
                this.logout();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
