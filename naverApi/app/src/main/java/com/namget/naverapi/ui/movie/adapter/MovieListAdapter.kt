package com.namget.naverapi.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.namget.naverapi.R
import com.namget.naverapi.data.model.Movie
import com.namget.naverapi.databinding.ItemMovieBinding

class MovieListAdapter(val list: MutableList<Movie>) : RecyclerView.Adapter<MovieListAdapter.mViewholder>() {


    class mViewholder(val itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {

        fun bind(movie: Movie) {
            itemMovieBinding.model = movie
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewholder {
        val view: ItemMovieBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie, parent, false)
        return mViewholder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: mViewholder, position: Int) {
        holder.bind(list[position])
    }
}