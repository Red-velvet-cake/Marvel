package com.red_velvet.marvel.ui.home

import com.red_velvet.marvel.R
import com.red_velvet.marvel.domain.models.Comic
import com.red_velvet.marvel.ui.base.BaseAdapter

class ComicsAdapter(
    items: List<Comic>,
    listener: HomeInteractionListener
) : BaseAdapter<Comic>(items, listener) {
    override val layoutId = R.layout.item_home_comic
}