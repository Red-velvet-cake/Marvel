package com.red_velvet.marvel.ui.seriesDetails

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.Creator
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class CreatorsAdapter(
    items: List<Creator>,
    listener: CreatorListenerInteraction,
) : BaseAdapter<Creator>(items, listener) {
    override val layoutId: Int = R.layout.item_creator
}

interface CreatorListenerInteraction : BaseInteractionListener