package com.red_velvet.marvel.ui.home

import com.red_velvet.marvel.R
import com.red_velvet.marvel.domain.models.Character
import com.red_velvet.marvel.ui.base.BaseAdapter

class CharactersAdapter(
    items: List<Character>,
    listener: HomeInteractionListener
) : BaseAdapter<Character>(items, listener) {
    override val layoutId = R.layout.item_home_character
}