package com.namget.naverapi.ui.search

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.namget.naverapi.data.remote.Repository
import com.namget.naverapi.ui.base.BaseViewModel
import com.namget.naverapi.util.Event
import com.namget.naverapi.util.htmlToString
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class SearchViewModel(val repository: Repository) : BaseViewModel() {

    private val _event: MutableLiveData<Event<String>> = MutableLiveData()
    val event: LiveData<Event<String>> get() = _event
    private val _titleInputText: MutableLiveData<String> = MutableLiveData()
    val titleInputText: LiveData<String> get() = _titleInputText

    private val _autoText: MutableLiveData<List<String>> = MutableLiveData()
    val autoText: LiveData<List<String>> get() = _autoText


    val autoTextBehaviorSubject = BehaviorSubject.create<String>()
    val SEARCH_CLICK = "SEARCH_CLICK"


    init {
        addDisposable(
            autoTextBehaviorSubject.subscribeOn(Schedulers.newThread())
                .throttleLast(2500, TimeUnit.MILLISECONDS)
                .concatMap {
                    Log.e("test", "test ${it}")
                    repository.getMovieTitleList(it, null).toObservable()
                }
                .map { it.map { it.title.htmlToString() } }
                .subscribe({
                    Log.e("test", "test ${it}")
                    _autoText.postValue(it)
                }, {
                    Log.e("test", "test ${it}")
                }, {

                })
        )
    }


    //내용 textwatcher
    fun onContentTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        _titleInputText.value = s.toString()
        autoTextBehaviorSubject.onNext(_titleInputText.value ?: "")
    }

    fun searchClick(view: View) {
        _event.value = Event(SEARCH_CLICK)
    }


}