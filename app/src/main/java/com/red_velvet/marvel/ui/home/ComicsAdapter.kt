package com.red_velvet.marvel.ui.home

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class ComicsAdapter(
    items: List<ComicsResponse>,
    listener: ComicsInteractionListener
) : BaseAdapter<ComicsResponse>(items, listener) {

    override val layoutId: Int
        get() = R.layout.item_comic
}


interface ComicsInteractionListener : BaseInteractionListener {
    fun onClickComic(comic: ComicsResponse)
}