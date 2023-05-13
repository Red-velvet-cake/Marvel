package com.red_velvet.marvel.ui.storyDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentStoryBinding
import com.red_velvet.marvel.ui.base.BaseFragment


class StoryDetailsFragment : BaseFragment<FragmentStoryBinding, StoryDetailsViewModel>() {

    override val layoutIdFragment: Int = R.layout.fragment_story

    override val viewModel: StoryDetailsViewModel by viewModels()

    private val args: StoryDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadStoryDetails(args.storyId)
        val storyCreatorAdapter = StoryCreatorAdapter(mutableListOf(), viewModel)
        binding.creatorRecyclerView.adapter = storyCreatorAdapter
        binding.textViewError.setOnClickListener { viewModel.loadStoryDetails(args.storyId) }
    }
}