package com.example.movies.repositories

import com.example.movies.model.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit.ApiInterface

class MovieRepository {

    private val tmdbService: ApiInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        tmdbService = retrofit.create(ApiInterface::class.java)
    }

    suspend fun fetchMovies(category: String): List<Movie> {
        val response = tmdbService.getMovies(category, ApiInterface.API_KEY)
        if (response.isSuccessful) {
            return response.body()?.results ?: emptyList()
        } else {
            throw Exception("Failed to fetch movies")
        }
    }
}