package com.namget.naverapi.data.remote

import com.namget.naverapi.data.model.MovieResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/search/movie.json")
    fun getMovieList(@Query("query") query: String, @Query("display") display: Int? = 20, @Query("start") start: Int = 1): Single<MovieResult>

}