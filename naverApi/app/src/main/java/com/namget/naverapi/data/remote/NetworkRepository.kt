package com.namget.naverapi.data.remote

import com.namget.naverapi.data.model.Movie
import com.namget.naverapi.data.model.MovieResult
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class NetworkRepository(val apiService: ApiService) : Repository {
    override fun getMovieList(query: String, display: Int?) : Single<MovieResult> {
        return apiService.getMovieList(query, display)
    }
}
