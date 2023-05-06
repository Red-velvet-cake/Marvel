package com.red_velvet.marvel.ui.serieDetails.adapter

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class CreatorsAdapter(
    items: List<CreatorsResponse>,
    listener: CreatorListenerInteraction,
) : BaseAdapter<CreatorsResponse>(items, listener) {
    override val layoutId: Int = R.layout.item_creator
}

interface CreatorListenerInteraction : BaseInteractionListener {
    fun onClickCreator(creator: CreatorsResponse)
}