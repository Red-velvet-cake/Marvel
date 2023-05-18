package com.red_velvet.marvel.ui.comics

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.dto.ComicDto
import com.red_velvet.marvel.ui.base.BaseAdapter

class ComicAdapter(
    items: List<ComicDto>,
    listener: ComicsInteractionListener
) : BaseAdapter<ComicDto>(items, listener) {
    override val layoutId = R.layout.item_comic
}