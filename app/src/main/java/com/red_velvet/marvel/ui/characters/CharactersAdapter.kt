package com.red_velvet.marvel.ui.characters

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.remote.dtos.CharacterDto
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class CharactersAdapter(
    items: List<CharacterDto>,
    listener: CharacterDetailsInteractionListener
) : BaseAdapter<CharacterDto>(items, listener) {
    override val layoutId: Int = R.layout.characters_item
}

interface CharacterDetailsInteractionListener : BaseInteractionListener {
    fun doOnCharacterClicked(characterId: Int)
}