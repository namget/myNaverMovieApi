package com.namget.naverapi.data.remote

import com.namget.naverapi.data.model.Movie
import com.namget.naverapi.data.model.MovieResult
import io.reactivex.Single

interface Repository {
    fun getMovieList(query: String, display: Int?, start: Int): Single<MovieResult>
    fun getMovieTitleList(query: String, display: Int?): Single<List<Movie>>
}