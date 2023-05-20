package com.red_velvet.marvel.ui.stories


import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.remote.dtos.Story
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class StoriesAdapter(stories: List<Story>, listener: StoriesInteractionListener) :
    BaseAdapter<Story>(stories, listener) {
    override val layoutId = R.layout.item_stories
}

interface StoriesInteractionListener : BaseInteractionListener {
    fun doOnStoryClicked(storyId: Int)
}