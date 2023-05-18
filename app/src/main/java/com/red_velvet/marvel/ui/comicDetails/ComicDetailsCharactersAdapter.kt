package com.red_velvet.marvel.ui.comicDetails

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.dto.CharacterDto
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class ComicDetailsCharactersAdapter(
    items: List<CharacterDto>,
    listener: ComicDetailsCharacterListenerInteraction,
) : BaseAdapter<CharacterDto>(items, listener) {
    override val layoutId: Int = R.layout.item_comic_character
}

interface ComicDetailsCharacterListenerInteraction : BaseInteractionListener {
    fun doOnCharacterClicked(characterId: Int)
}