package com.namget.naverapi.ui.movie

import android.os.Bundle
import com.namget.naverapi.R
import com.namget.naverapi.databinding.ActivityMovielistBinding
import com.namget.naverapi.ui.base.BaseActivity
import com.namget.naverapi.util.Extra

class MovieListActivity : BaseActivity<ActivityMovielistBinding>() {


    override val layoutId: Int = R.layout.activity_movielist


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        intent.extras?.let {
            it.getString(Extra.MOVIE_NAME)
        }
    }


}