package com.red_velvet.marvel.ui.series

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.remote.dto.SeriesDto
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class SeriesAdapter(items: List<SeriesDto>, listener: SeriesInteractionListener) :
    BaseAdapter<SeriesDto>(items, listener) {
    override val layoutId: Int = R.layout.item_series
}

interface SeriesInteractionListener : BaseInteractionListener {
    fun doOnSeriesClicked(seriesId: Int)
}