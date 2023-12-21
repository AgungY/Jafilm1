package com.example.jafilm1;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieResponse{
    @SerializedName("page")
    public int page;

    @SerializedName("results")
    public List<Movie> results;

    @SerializedName("total_pages")
    public int total_pages;

    @SerializedName("total_results")
    public int total_results;
}
