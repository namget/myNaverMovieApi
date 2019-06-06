package com.namget.naverapi.ui.movie

import androidx.constraintlayout.utils.widget.MockView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.namget.naverapi.data.remote.ApiService

class MovieViewModelFactory(val apiService: ApiService) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(apiService) as T
    }
}