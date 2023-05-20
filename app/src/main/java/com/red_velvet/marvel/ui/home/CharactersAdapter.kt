package com.red_velvet.marvel.ui.home

import com.red_velvet.marvel.R
import com.red_velvet.marvel.domain.models.Charcter
import com.red_velvet.marvel.ui.base.BaseAdapter

class CharactersAdapter(
    items: List<Charcter>,
    listener: HomeInteractionListener
) : BaseAdapter<Charcter>(items, listener) {
    override val layoutId = R.layout.item_home_character
}