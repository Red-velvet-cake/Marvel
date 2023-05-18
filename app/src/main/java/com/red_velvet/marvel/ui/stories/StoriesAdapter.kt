package com.red_velvet.marvel.ui.stories


import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.dto.StoryDto
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class StoriesAdapter(stories: List<StoryDto>, listener: StoriesInteractionListener) :
    BaseAdapter<StoryDto>(stories, listener) {
    override val layoutId = R.layout.item_stories
}

interface StoriesInteractionListener : BaseInteractionListener {
    fun doOnStoryClicked(storyId: Int)
}