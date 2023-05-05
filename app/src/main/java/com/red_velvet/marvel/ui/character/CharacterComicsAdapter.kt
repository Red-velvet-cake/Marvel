package com.red_velvet.marvel.ui.character

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class ComicsByCharacterAdapter(items: List<ComicsResponse>, listener: SeriesInteractionListener) :
    BaseAdapter<ComicsResponse>(items, listener) {
    override val layoutId: Int = R.layout.item_character_comics
}

interface ComicsInteractionListener : BaseInteractionListener {
}