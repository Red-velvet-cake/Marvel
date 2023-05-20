package com.red_velvet.marvel.ui.home.adapter

import com.red_velvet.marvel.R
import com.red_velvet.marvel.domain.model.Character
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.home.HomeInteractionListener

class CharactersAdapter(
    items: List<Character>,
    listener: HomeInteractionListener
) : BaseAdapter<Character>(items, listener) {
    override val layoutId = R.layout.item_home_character
}