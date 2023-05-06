package com.red_velvet.marvel.ui.comicDetails.adapter

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.databinding.ComicItemNestedBinding
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener
import com.red_velvet.marvel.ui.comicDetails.ParentComicItem

class ParentComicAdapter(
    private val items: List<ParentComicItem>,
    private val listener: BaseInteractionListener,
) : BaseAdapter<ParentComicItem>(items, listener) {
    override val layoutId: Int = R.layout.comic_item_nested

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val currentItem = items[position]
        when (holder) {
            is ItemViewHolder -> {
                val dataHolder = holder.binding as ComicItemNestedBinding
                dataHolder.title.text = currentItem.title
                when (currentItem.items?.firstOrNull()) {
                    is CreatorsResponse -> {
                        dataHolder.childRecyclerView.adapter = ComicDetailsCreatorsAdapter(
                            currentItem.items as List<CreatorsResponse>,
                            listener as ComicDetailsCreatorListenerInteraction
                        )
                    }

                    is CharactersResponse -> {
                        dataHolder.childRecyclerView.adapter = ComicDetailsCharactersAdapter(
                            currentItem.items as List<CharactersResponse>,
                            listener as ComicDetailsCharacterListenerInteraction
                        )
                    }
                }
            }
        }
    }
}
