package com.red_velvet.marvel.ui.home.adapter

import com.red_velvet.marvel.R
import com.red_velvet.marvel.domain.model.Event
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.home.HomeInteractionListener

class EventsAdapter(
    items: List<Event>,
    listener: HomeInteractionListener
) : BaseAdapter<Event>(items, listener) {
    override val layoutId = R.layout.item_home_event
}