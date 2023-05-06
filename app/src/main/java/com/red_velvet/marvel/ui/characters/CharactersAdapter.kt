package com.red_velvet.marvel.ui.characters

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.ui.base.BaseAdapter

class CharactersAdapter(
    items: List<CharactersResponse>,
    listener: CharacterDetailsInteractionListener
) : BaseAdapter<CharactersResponse>(items, listener) {
    override val layoutId: Int = R.layout.characters_item
}