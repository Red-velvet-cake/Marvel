package com.red_velvet.marvel.ui.serieDetails.adapter

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.ui.base.BaseAdapter

class CreatorsAdapter(
    items:List<CreatorsResponse>,
    listener:CreatorListenerInteraction
): BaseAdapter<CreatorsResponse>(items,listener) {
    override val layoutId: Int = R.layout.item_creator
}