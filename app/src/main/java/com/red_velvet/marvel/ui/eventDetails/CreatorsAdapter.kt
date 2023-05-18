package com.red_velvet.marvel.ui.eventDetails

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.dto.CreatorDto
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class CreatorsAdapter(items: List<CreatorDto>, listener: CreatorsInteractionListener) :
    BaseAdapter<CreatorDto>(items, listener) {
    override val layoutId: Int = R.layout.item_creators_event
}

interface CreatorsInteractionListener : BaseInteractionListener