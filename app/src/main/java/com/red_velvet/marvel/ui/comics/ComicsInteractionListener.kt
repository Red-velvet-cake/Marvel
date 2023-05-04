package com.red_velvet.marvel.ui.comics

import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.ui.base.BaseInteractionListener

interface ComicsInteractionListener : BaseInteractionListener {
    fun onComicClicked(comic: ComicsResponse)
}