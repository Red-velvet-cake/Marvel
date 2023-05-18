package com.red_velvet.marvel.ui.characterDetails

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.dto.ComicDto
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class ComicsByCharacterAdapter(items: List<ComicDto>, listener: SeriesInteractionListener) :
    BaseAdapter<ComicDto>(items, listener) {
    override val layoutId: Int = R.layout.item_character_comics
}

interface ComicsInteractionListener : BaseInteractionListener {
    fun doOnComicClicked(comicId: Int)
}