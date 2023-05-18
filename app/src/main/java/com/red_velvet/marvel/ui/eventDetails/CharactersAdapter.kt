package com.red_velvet.marvel.ui.eventDetails

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.dto.CharacterDto
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class CharactersAdapter(items: List<CharacterDto>, listener: CharactersInteractionListener) :
    BaseAdapter<CharacterDto>(items, listener) {
    override val layoutId: Int = R.layout.item_charactors
}

interface CharactersInteractionListener : BaseInteractionListener {
    fun doOnCharacterClicked(characterId: Int)
}