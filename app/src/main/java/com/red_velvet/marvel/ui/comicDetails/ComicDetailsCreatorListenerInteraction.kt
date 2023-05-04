package com.red_velvet.marvel.ui.comicDetails

import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.ui.base.BaseInteractionListener

interface ComicDetailsCreatorListenerInteraction : BaseInteractionListener {
    fun onClickCreator(creator: CreatorsResponse)
}