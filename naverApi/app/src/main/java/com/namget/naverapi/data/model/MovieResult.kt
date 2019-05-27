package com.namget.naverapi.data.model

import android.view.Display
import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieResult(
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<Movie>
)