package com.namget.naverapi.data.model

import android.view.Display
import java.util.*

data class MovieResult(val date : Date,
                       val total : Int,
                       val start : Int,
                       val display: Int,
                       val items : List<Movie>
)