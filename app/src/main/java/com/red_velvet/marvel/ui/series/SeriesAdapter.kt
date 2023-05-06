package com.red_velvet.marvel.ui.series

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.ui.base.BaseAdapter

class SeriesAdapter(items:List<SeriesResponse>, listener: SeriesViewModel)
    : BaseAdapter<SeriesResponse>(items,listener){
    override val layoutId: Int= R.layout.item_series
}