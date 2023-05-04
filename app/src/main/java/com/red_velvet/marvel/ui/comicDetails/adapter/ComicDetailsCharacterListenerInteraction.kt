package com.red_velvet.marvel.ui.comicDetails.adapter

import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.ui.base.BaseInteractionListener

interface ComicDetailsCharacterListenerInteraction : BaseInteractionListener {
    fun onClickCharacter(creator: CreatorsResponse)
}