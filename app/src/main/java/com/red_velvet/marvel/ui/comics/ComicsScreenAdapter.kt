package com.red_velvet.marvel.ui.comics

import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.ListComicsBinding
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class ComicsScreenAdapter(
    items: List<ComicsCollection>,
    private val listener: ComicsInteractionListener,
) : BaseAdapter<ComicsCollection>(items, listener) {
    override val layoutId = R.layout.list_comics

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        when (holder) {
            is ItemViewHolder -> {
                (holder.binding as ListComicsBinding).recyclerComics.adapter = ComicAdapter(
                    emptyList(),
                    listener
                )
            }
        }
    }
}

interface ComicsInteractionListener : BaseInteractionListener {
    fun doOnComicClicked(comicId: Int)
}