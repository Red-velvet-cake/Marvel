package com.red_velvet.marvel.ui.eventDetails

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.ui.base.BaseAdapter

class CreatorsAdapter(items: List<CreatorsResponse>) : BaseAdapter<CreatorsResponse>(items) {
    override val layoutId: Int = R.layout.item_creators_event
}