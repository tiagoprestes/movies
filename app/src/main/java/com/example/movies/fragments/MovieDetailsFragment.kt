package com.example.movies.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movies.databinding.FragmentMovieDetailsBinding
import com.example.movies.model.Movie

class MovieDetailsFragment : Fragment()  {

    companion object {
        fun newInstance(
            movie: Movie
        ): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()

            val bundle = Bundle()
            bundle.putParcelable("movie", movie)

            fragment.arguments = bundle

            return fragment
        }
    }

    private var _binding: FragmentMovieDetailsBinding? = null
    private val mBinding: FragmentMovieDetailsBinding get() = _binding!!

    private lateinit var movieSelected: Movie

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)

        if (savedInstanceState!= null) {
            loadExtras(savedInstanceState)
        } else {
            loadExtras(this.requireArguments())
        }

        return mBinding.root
    }

    private fun loadExtras(bundle: Bundle) {
        movieSelected = bundle.getParcelable("movie")!!
        _binding?.textTitle?.text = movieSelected.title
        _binding?.textOverview?.setText(movieSelected.overview)
    }

}
