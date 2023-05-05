package com.red_velvet.marvel.ui.comicDetails.adapter

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.ui.base.BaseAdapter

class ComicDetailsCreatorsAdapter(
    items: List<CreatorsResponse>,
    listener: ComicDetailsCreatorListenerInteraction,
) : BaseAdapter<CreatorsResponse>(items, listener) {
    override val layoutId: Int = R.layout.comic_item_creator
}