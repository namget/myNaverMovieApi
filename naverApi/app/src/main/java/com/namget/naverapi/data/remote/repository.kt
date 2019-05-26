package com.namget.naverapi.data.remote

import com.namget.naverapi.data.model.MovieResult
import io.reactivex.Single

interface Repository {
    fun getMovieList(query: String, display: Int?): Single<MovieResult>
}