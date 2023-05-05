package com.red_velvet.marvel.ui.comics

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.ui.base.BaseAdapter

class ComicAdapter(
    items: List<ComicsResponse>,
    listener: ComicsInteractionListener
) : BaseAdapter<ComicsResponse>(items, listener) {

    override val layoutId = R.layout.item_comic
}