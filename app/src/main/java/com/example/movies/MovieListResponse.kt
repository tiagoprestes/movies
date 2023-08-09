package com.example.movies

import com.example.movies.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("results") val results: List<Movie>,
)