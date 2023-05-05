package com.red_velvet.marvel.ui.eventDetails

import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.ui.base.BaseInteractionListener

interface CreatorListenerInteraction :BaseInteractionListener{

    fun onClickCreator(creatorsResponse: CreatorsResponse)
}