package com.red_velvet.marvel.ui.storyDetails

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentStoryBinding
import com.red_velvet.marvel.ui.base.BaseFragment


class StoryDetailsFragment : BaseFragment<FragmentStoryBinding,StoryDetailsViewModel>() {
    override val layoutIdFragment: Int = R.layout.fragment_story
    override val viewModel: StoryDetailsViewModel by viewModels()

    private val args: StoryDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val storyId = args.storyId
        viewModel.getStory(storyId)
        viewModel.getStoryComics(storyId)
        viewModel.getStoryCreators(storyId)
    }

    override fun setUp() {
        val storyCreatorAdapter = StoryCreatorAdapter(mutableListOf(), viewModel)
        binding.creatorRecyclerView.adapter = storyCreatorAdapter
    }

}