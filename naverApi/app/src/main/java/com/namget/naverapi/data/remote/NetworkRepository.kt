package com.namget.naverapi.data.remote

import androidx.lifecycle.Transformations.map
import com.namget.naverapi.data.model.Movie
import com.namget.naverapi.data.model.MovieResult
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers

class NetworkRepository(val apiService: ApiService) : Repository {


    override fun getMovieList(query: String, display: Int?, start: Int): Single<MovieResult> =
        apiService.getMovieList(query, display, start)


    override fun getMovieTitleList(query: String, display: Int?): Single<List<Movie>> {
        return apiService.getMovieList(query, display).subscribeOn(Schedulers.io())
            .map { it.items }
    }
}
