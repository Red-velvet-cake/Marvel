package com.red_velvet.marvel.ui.comicDetails

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.Character
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class ComicDetailsCharactersAdapter(
    items: List<Character>,
    listener: ComicDetailsCharacterListenerInteraction,
) : BaseAdapter<Character>(items, listener) {
    override val layoutId: Int = R.layout.comic_item_character
}

interface ComicDetailsCharacterListenerInteraction : BaseInteractionListener {
    fun onClickCharacter(characterId: Int)
}