package com.red_velvet.marvel.ui.eventDetails

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.Character
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class CharactersAdapter(items: List<Character>, listener: CharactersInteractionListener) :
    BaseAdapter<Character>(items, listener) {
    override val layoutId: Int = R.layout.item_charactors
}

interface CharactersInteractionListener : BaseInteractionListener {
    fun doOnCharacterClicked(characterId: Int)
}