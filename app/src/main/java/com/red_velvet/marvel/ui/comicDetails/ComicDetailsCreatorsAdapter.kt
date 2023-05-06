package com.red_velvet.marvel.ui.comicDetails

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class ComicDetailsCreatorsAdapter(
    items: List<CreatorsResponse>,
    listener: ComicDetailsCreatorListenerInteraction,
) : BaseAdapter<CreatorsResponse>(items, listener) {
    override val layoutId: Int = R.layout.comic_item_creator
}

interface ComicDetailsCreatorListenerInteraction : BaseInteractionListener {
    fun onClickCreator(creator: CreatorsResponse)
}