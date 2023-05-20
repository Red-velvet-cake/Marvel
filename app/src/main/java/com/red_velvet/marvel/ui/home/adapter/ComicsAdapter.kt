package com.red_velvet.marvel.ui.home.adapter

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.remote.dtos.ComicDto
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.home.HomeInteractionListener

class ComicsAdapter(
    items: List<ComicDto>,
    listener: HomeInteractionListener
) : BaseAdapter<ComicDto>(items, listener) {
    override val layoutId = R.layout.item_home_comic
}