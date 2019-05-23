package com.namget.naverapi.ui.search

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.namget.naverapi.data.remote.Repository
import com.namget.naverapi.ui.base.BaseViewModel
import com.namget.naverapi.util.Event

class SearchViewModel() : BaseViewModel() {

    private val _event: MutableLiveData<Event<String>> = MutableLiveData()
    val event: LiveData<Event<String>> get() = _event
    private val _title: MutableLiveData<String> = MutableLiveData()
    val title: LiveData<String> get() = _title

    val SEARCH_CLICK = "SEARCH_CLICK"

    //내용 textwatcher
    fun onContentTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        _title.value = s.toString()
    }

    fun searchClick(view : View){
        _event.value = Event(SEARCH_CLICK)
    }


}