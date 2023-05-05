package com.red_velvet.marvel.ui.serieDetails.adapter

import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.ui.base.BaseInteractionListener

interface CreatorListenerInteraction : BaseInteractionListener {
    fun onClickCreator(creator: CreatorsResponse)
}