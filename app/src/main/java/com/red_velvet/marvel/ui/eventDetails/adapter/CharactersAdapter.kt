package com.red_velvet.marvel.ui.eventDetails.adapter

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class CharactersAdapter (items:List<CharactersResponse>,

): BaseAdapter<CharactersResponse>(items,) {
    override val layoutId: Int = R.layout.item_charactors
}