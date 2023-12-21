package com.example.jafilm1;

import com.google.gson.annotations.SerializedName;

public class Movie {

        @SerializedName("adult")
        public boolean adult = false;

        @SerializedName("backdrop_path")
        public String backdrop_path;
        @SerializedName("genre_ids")
        public int[] genre_ids;
        @SerializedName("id")
        public int id;
        @SerializedName("original_language")
        public String original_language;
        @SerializedName("original_title")
        public String original_title;
        @SerializedName("overview")
        public String overview;
        @SerializedName("popularity")
        public double popularity;
        @SerializedName("poster_path")
        public String poster_path;
        @SerializedName("release_date")
        public String release_date;
        @SerializedName("title")
        public String title;
        @SerializedName("video")
        public boolean video;
        @SerializedName("vote_average")
        public double vote_average;
        @SerializedName("vote_count")
        public int vote_count;
    }
