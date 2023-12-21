package com.example.jafilm1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TmdbApiManager {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "9fec5e6dcf5e4ebcfdf94660a0afa65e";

    public static TmdbApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(TmdbApiService.class);
    }
}
