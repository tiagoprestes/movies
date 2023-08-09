package com.example.movies.viewModel

import androidx.lifecycle.*
import com.example.movies.model.Movie
import com.example.movies.repositories.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val repository = MovieRepository()

    fun fetchMovies(category: String) {
        viewModelScope.launch {
            val moviesList = repository.fetchMovies(category)
            _movies.value = moviesList
        }
    }
}