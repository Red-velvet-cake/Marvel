package com.red_velvet.marvel.ui.events

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.EventsResponse
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class EventsAdapter(items: List<EventsResponse>, listener: EventsInteractionListener) :
    BaseAdapter<EventsResponse>(items, listener) {

    override val layoutId: Int = R.layout.item_event
}

interface EventsInteractionListener : BaseInteractionListener {
    fun onEventClicked(eventId: Int)
}