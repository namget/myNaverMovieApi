package com.namget.naverapi.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.namget.naverapi.R
import com.namget.naverapi.databinding.ActivitySearchBinding
import com.namget.naverapi.ui.base.BaseActivity
import com.namget.naverapi.ui.movie.MovieListActivity
import com.namget.naverapi.util.Extra

class SearchActivity : BaseActivity<ActivitySearchBinding>() {

    override val layoutId: Int = R.layout.activity_search
    lateinit var searchViewModel: SearchViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    fun initViewModel() {
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        binding.viewmodel = searchViewModel

        searchViewModel.event.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                if (it == searchViewModel.SEARCH_CLICK) {
                    val intent = Intent(this@SearchActivity, MovieListActivity::class.java)
                    intent.putExtra(Extra.MOVIE_NAME, searchViewModel.title.value)
                    startActivity(intent)
                }
            }
        })


        binding.lifecycleOwner = this
    }
}