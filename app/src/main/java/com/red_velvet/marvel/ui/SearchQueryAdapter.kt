package com.red_velvet.marvel.ui

import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class SearchQueryAdapter(
    items: List<String>,
    listener: SearchInteractionListener
) : BaseAdapter<String>(items, listener) {
    override val layoutId: Int = com.red_velvet.marvel.R.layout.item_search_query
}

interface SearchInteractionListener : BaseInteractionListener {
    fun doOnSearchQueryClicked(query: String)
    fun doOnSearchQueryDeleteClicked(id: Int)
}