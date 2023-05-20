package com.red_velvet.marvel.ui.comicDetails

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.remote.dto.CreatorDto
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class ComicDetailsCreatorsAdapter(
    items: List<CreatorDto>,
    listener: ComicDetailsCreatorListenerInteraction,
) : BaseAdapter<CreatorDto>(items, listener) {
    override val layoutId: Int = R.layout.item_comic_creator
}

interface ComicDetailsCreatorListenerInteraction : BaseInteractionListener