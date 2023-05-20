package com.red_velvet.marvel.ui.home

import com.red_velvet.marvel.R
import com.red_velvet.marvel.domain.models.Event
import com.red_velvet.marvel.ui.base.BaseAdapter

class EventsAdapter(
    items: List<Event>,
    listener: HomeInteractionListener
) : BaseAdapter<Event>(items, listener) {
    override val layoutId = R.layout.item_home_event
}