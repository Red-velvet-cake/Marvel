package com.red_velvet.marvel.ui.eventDetails

import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.ui.base.BaseInteractionListener

interface CharactersListenerInteraction :BaseInteractionListener{

    fun onClickCharacters(charactersResponse: CharactersResponse)
}