package com.red_velvet.marvel.ui.character

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.Comic
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class ComicsByCharacterAdapter(items: List<Comic>, listener: SeriesInteractionListener) :
    BaseAdapter<Comic>(items, listener) {
    override val layoutId: Int = R.layout.item_character_comics
}

interface ComicsInteractionListener : BaseInteractionListener {
}