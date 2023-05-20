package com.red_velvet.marvel.ui.eventDetails

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.remote.dtos.Creator
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class CreatorsAdapter(items: List<Creator>, listener: CreatorsInteractionListener) :
    BaseAdapter<Creator>(items, listener) {
    override val layoutId: Int = R.layout.item_creators_event
}

interface CreatorsInteractionListener : BaseInteractionListener