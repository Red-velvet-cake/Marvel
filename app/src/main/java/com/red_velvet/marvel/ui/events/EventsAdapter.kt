package com.red_velvet.marvel.ui.events

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.remote.dtos.EventDto
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class EventsAdapter(items: List<EventDto>, listener: EventsInteractionListener) :
    BaseAdapter<EventDto>(items, listener) {

    override val layoutId: Int = R.layout.item_event
}

interface EventsInteractionListener : BaseInteractionListener {
    fun doOnEventClicked(eventId: Int)
}