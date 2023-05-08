package com.red_velvet.marvel.ui.comics

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.Comic
import com.red_velvet.marvel.ui.base.BaseAdapter

class ComicAdapter(
    items: List<Comic>,
    listener: ComicsInteractionListener
) : BaseAdapter<Comic>(items, listener) {

    override val layoutId = R.layout.item_card
}