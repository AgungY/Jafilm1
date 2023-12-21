package com.example.jafilm1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private FirebaseUser firebaseUser;
    private TextView textNama;
    private Button btnLogout;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textNama = findViewById(R.id.Nama);
        btnLogout = findViewById(R.id.btnLogout);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if(firebaseUser!=null){
            textNama.setText(firebaseUser.getDisplayName());
        }else{
            textNama.setText("Login Gagal!");
        }
        btnLogout.setOnClickListener(v-> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });

        this.getMoviesData();
    }


    void getMoviesData() {
        TmdbApiService api = TmdbApiManager.getApiService();

        Call<MovieResponse> call = api.getPopularMovies(TmdbApiManager.API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                Log.d("RESPONSE", response.toString());
                MovieResponse res = response.body();


            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {

            }
        });
    }
}
