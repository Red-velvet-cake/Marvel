package com.red_velvet.marvel.ui.characterDetails

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.remote.dtos.SeriesDto
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class CharacterSeriesAdapter(items: List<SeriesDto>, listener: SeriesInteractionListener) :
    BaseAdapter<SeriesDto>(items, listener) {
    override val layoutId: Int = R.layout.item_character_series
}

interface SeriesInteractionListener : BaseInteractionListener {
    fun doOnSeriesClicked(seriesId: Int)
}