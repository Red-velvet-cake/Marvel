package com.red_velvet.marvel.ui.home.adapter

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.remote.dtos.CharacterDto
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.home.HomeInteractionListener

class CharactersAdapter(
    items: List<CharacterDto>,
    listener: HomeInteractionListener
) : BaseAdapter<CharacterDto>(items, listener) {
    override val layoutId = R.layout.item_home_character
}