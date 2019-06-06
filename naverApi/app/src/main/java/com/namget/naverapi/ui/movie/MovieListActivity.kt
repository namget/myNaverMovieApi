package com.namget.naverapi.ui.movie

import android.os.Bundle
import androidx.databinding.adapters.LinearLayoutBindingAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.namget.naverapi.R
import com.namget.naverapi.data.model.Movie
import com.namget.naverapi.databinding.ActivityMovielistBinding
import com.namget.naverapi.ui.base.BaseActivity
import com.namget.naverapi.ui.movie.adapter.MovieListAdapter
import com.namget.naverapi.util.Extra
import kotlinx.android.synthetic.main.activity_movielist.*
import org.koin.android.ext.android.inject

class MovieListActivity : BaseActivity<ActivityMovielistBinding>() {


    override val layoutId: Int = R.layout.activity_movielist
    var movieName: String = ""
    lateinit var movieViewModel: MovieViewModel
    val movieViewModelFactory: MovieViewModelFactory by inject()
    val movieList: MutableList<Movie> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initViewModel()
    }

    fun initViewModel() {
        movieViewModel = ViewModelProviders.of(this, movieViewModelFactory).get(MovieViewModel::class.java)

        binding.lifecycleOwner = this
    }

    fun init() {
        intent.extras?.let {
            movieName = it.getString(Extra.MOVIE_NAME)
        }
        movieRecyclerView.apply {
            this.adapter = MovieListAdapter(movieList)
            this.layoutManager = LinearLayoutManager(this@MovieListActivity)
        }

    }


}