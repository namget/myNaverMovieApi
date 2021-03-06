package com.namget.naverapi.di

import com.google.gson.GsonBuilder
import com.namget.naverapi.BuildConfig
import com.namget.naverapi.data.remote.ApiService
import com.namget.naverapi.data.remote.NetworkRepository
import com.namget.naverapi.data.remote.Repository
import com.namget.naverapi.ui.movie.MovieViewModelFactory
import com.namget.naverapi.ui.search.SearchViewModelFactory
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
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
        val headerInterceptor = Interceptor {
            val original = it.request()
            val request = original.newBuilder()
                .header("X-Naver-Client-Id", "lih3bjz8wm5kjJhL8Grx")
                .header("X-Naver-Client-Secret", "pNsddDIvgZ")
                .method(original.method(), original.body())
                .build()
            it.proceed(request)
        }

        val client = OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(headerInterceptor).build()


        Retrofit.Builder().apply {
            addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            addConverterFactory(GsonConverterFactory.create(gson))
            client(client)
            baseUrl(BASEURL)
        }.build().create(ApiService::class.java)
    }



    single {
        NetworkRepository(get()) as Repository
    }
}
val modelModule = module {
    factory {
        SearchViewModelFactory(get())
    }
    factory {
        MovieViewModelFactory(get())
    }
}

val appModules = listOf(apiModules, modelModule)
