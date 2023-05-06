package com.red_velvet.marvel.ui.characters

import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.ui.base.BaseInteractionListener

interface CharacterDetailsInteractionListener : BaseInteractionListener {
    fun onCharacterSelected(character: CharactersResponse)
}