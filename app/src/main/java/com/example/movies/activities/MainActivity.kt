package com.example.movies.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.movies.adapter.MovieAdapter
import com.example.movies.databinding.ActivityMainBinding
import com.example.movies.fragments.MovieDetailsFragment
import com.example.movies.model.Movie
import com.example.movies.viewModel.MovieViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        setupListeners()
        observeViewModel()
    }

    private fun setupListeners() {
        binding.nowPlayingButton.setOnClickListener { viewModel.fetchMovies("now_playing") }
        binding.popularButton.setOnClickListener { viewModel.fetchMovies("popular") }
        binding.topRatedButton.setOnClickListener { viewModel.fetchMovies("top_rated") }
        binding.upcomingButton.setOnClickListener { viewModel.fetchMovies("upcoming") }
    }

    private fun observeViewModel() {

        viewModel.movies.observe(this) { moviesList ->
            setMovies(moviesList)
        }

    }

    private fun setMovies(moviesList: List<Movie>?) {
        binding.moviesRecyclerView.adapter = moviesList?.let {
            MovieAdapter(
                it,
                object : MovieAdapter.ClickItemListener {
                    override fun onClicked(model: Any, position: Int) {
                        loadFragment(MovieDetailsFragment.newInstance(it.get(position)))
                    }
                })
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(com.example.movies.R.id.fragmentContainer, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
