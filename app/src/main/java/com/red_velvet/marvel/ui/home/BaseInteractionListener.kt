package com.red_velvet.marvel.ui.home

import com.red_velvet.marvel.ui.base.BaseInteractionListener

interface HomeInteractionListener : BaseInteractionListener {
    fun doOnComicClicked(comicId: Int)
    fun doOnEventClicked(eventId: Int)
    fun doOnCharacterClicked(charId: Int)
}