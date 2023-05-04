package com.red_velvet.marvel.ui.comicDetails

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.ui.base.BaseAdapter

class ComicDetailsCharactersAdapter(
    items: List<CharactersResponse>,
    listener: ComicDetailsCharacterListenerInteraction,
) : BaseAdapter<CharactersResponse>(items, listener) {
    override val layoutId: Int = R.layout.comic_item_character
}