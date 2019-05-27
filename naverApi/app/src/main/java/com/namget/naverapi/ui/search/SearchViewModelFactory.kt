package com.namget.naverapi.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.namget.naverapi.data.remote.ApiService
import com.namget.naverapi.data.remote.Repository

@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory(val repository: Repository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }
}