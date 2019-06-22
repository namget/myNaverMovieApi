package com.namget.naverapi.ui.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.namget.naverapi.R
import com.namget.naverapi.databinding.ActivitySearchBinding
import com.namget.naverapi.ui.base.BaseActivity
import com.namget.naverapi.ui.movie.MovieListActivity
import com.namget.naverapi.ui.search.adapter.SearchAdapter
import com.namget.naverapi.util.Extra
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.android.ext.android.inject

class SearchActivity : BaseActivity<ActivitySearchBinding>() {

    override val layoutId: Int = R.layout.activity_search
    lateinit var searchViewModel: SearchViewModel
    lateinit var searchAdapter: SearchAdapter
    val searchViewModelFactory: SearchViewModelFactory by inject()
    val searchList: ArrayList<String> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initViewModel()

        Log.d("MyTag","충돌")
        Log.d("MyTag","충돌2")

        Log.d("MyTag","BlackJIn")
        Log.d("MyTag","내맘이야")
    }

    fun initViewModel() {
        searchViewModel = ViewModelProviders.of(this, searchViewModelFactory).get(SearchViewModel::class.java)
        binding.viewmodel = searchViewModel

        searchViewModel.initAutoSet()
        searchViewModel.event.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                if (it == searchViewModel.SEARCH_CLICK) {
                    val intent = Intent(this@SearchActivity, MovieListActivity::class.java)
                    intent.putExtra(Extra.MOVIE_NAME, searchViewModel.titleInputText.value)
                    startActivity(intent)
                }
            }
        })

        searchViewModel.autoText.observe(this, Observer {
            setAutoSearchView(it)
        })


        binding.lifecycleOwner = this
    }

    fun init() {
        searchAdapter = SearchAdapter(this, android.R.layout.simple_dropdown_item_1line, searchList)
        autoSearchView.setAdapter(searchAdapter)
        autoSearchView.setOnItemClickListener { _, _: View?, p3: Int, _: Long ->
            Log.e("test", "" + searchAdapter.getObject(p3))
        }


    }

    fun setAutoSearchView(list: List<String>) {
        searchAdapter.setData(list)
        searchAdapter.notifyDataSetChanged()
    }


}