package com.red_velvet.marvel.ui.story

import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentStoryBinding
import com.red_velvet.marvel.ui.base.BaseFragment


class StoryFragment : BaseFragment<FragmentStoryBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_story
    override val viewModel: StoryViewModel by viewModels()

    override fun setUp() {
        viewModel.getStory(8)
        viewModel.getStoryComics(8)
        viewModel.getStoryCreators(8)
        val storyCreatorAdapter = StoryCreatorAdapter(mutableListOf(), viewModel)
        binding.creatorRecyclerView.adapter = storyCreatorAdapter
    }

}