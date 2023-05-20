package com.red_velvet.marvel.ui.characters

import com.red_velvet.marvel.R
import com.red_velvet.marvel.domain.models.Character
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class CharactersAdapter(
    items: List<Character>,
    listener: CharacterDetailsInteractionListener
) : BaseAdapter<Character>(items, listener) {
    override val layoutId: Int = R.layout.characters_item
}

interface CharacterDetailsInteractionListener : BaseInteractionListener {
    fun doOnCharacterClicked(characterId: Int)
}