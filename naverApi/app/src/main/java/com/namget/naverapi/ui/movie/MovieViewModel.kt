package com.namget.naverapi.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.namget.naverapi.data.model.MovieResult
import com.namget.naverapi.data.remote.ApiService
import com.namget.naverapi.ui.base.BaseViewModel
import io.reactivex.schedulers.Schedulers

class MovieViewModel(val apiService: ApiService) : BaseViewModel() {
    private val _movieList: MutableLiveData<MovieResult> = MutableLiveData()
    val movieList: LiveData<MovieResult> get() = _movieList

    fun researchMovie(name: String, start: Int) {
        apiService.getMovieList(query = name, start = start)
            .subscribeOn(Schedulers.newThread())
            .subscribe({

            }, {

            })
    }

}