package com.red_velvet.marvel.ui.comics

import com.red_velvet.marvel.R
import com.red_velvet.marvel.ui.base.BaseAdapter

class CollectionAdapter(
    items: List<ComicsCollection>,
    listener: ComicsInteractionListener
) : BaseAdapter<ComicsCollection>(items, listener) {

    override val layoutId = R.layout.item_comic
}