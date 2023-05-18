package com.red_velvet.marvel.ui.storyDetails

import com.red_velvet.marvel.R
import com.red_velvet.marvel.data.dto.CreatorDto
import com.red_velvet.marvel.ui.base.BaseAdapter
import com.red_velvet.marvel.ui.base.BaseInteractionListener

class StoryCreatorAdapter(
    items: List<CreatorDto>,
    listener: StoryCreatorInteractionListener
) :
    BaseAdapter<CreatorDto>(items, listener) {
    override val layoutId: Int = R.layout.item_story_creator
}

interface StoryCreatorInteractionListener : BaseInteractionListener