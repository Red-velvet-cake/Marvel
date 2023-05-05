package com.red_velvet.marvel.ui.stories


import com.red_velvet.marvel.data.model.StoryResponse
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class StoriesAdapter(stories: List<StoryResponse>, listener: BaseInteractionListener) :
    BaseAdapter<StoryResponse>(stories, listener) {
    override val layoutId: Int
        get() = TODO("Not yet implemented")

}