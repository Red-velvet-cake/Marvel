package com.red_velvet.marvel.ui.home.adapter

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.Comic
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.home.HomeInteractionListener

class ComicsAdapter(
    items: List<Comic>,
    listener: HomeInteractionListener
) : BaseAdapter<Comic>(items, listener) {
    override val layoutId = R.layout.item_home_comic
}