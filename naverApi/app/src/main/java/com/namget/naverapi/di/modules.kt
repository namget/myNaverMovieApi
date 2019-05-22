package com.namget.naverapi.di

import com.google.gson.GsonBuilder
import com.namget.naverapi.BuildConfig
import com.namget.naverapi.data.remote.ApiService
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASEURL = "https://openapi.naver.com"

val apiModules: Module = module {
    single {
        val gson = GsonBuilder().setLenient().create()

        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()


        Retrofit.Builder().apply {
            addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            addConverterFactory(GsonConverterFactory.create(gson))
            client(client)
            baseUrl(BASEURL)
        }.build().create(ApiService::class.java)
    }

}
val appModules = listOf(apiModules)
