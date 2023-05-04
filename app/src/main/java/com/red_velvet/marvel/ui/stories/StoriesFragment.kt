package com.red_velvet.marvel.ui.stories

import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentStoriesBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class StoriesFragment : BaseFragment<FragmentStoriesBinding>() {
    override val layoutIdFragment = R.layout.fragment_stories
    override val viewModel: StoriesViewModel by viewModels()

    override fun setUp() {
        binding.root.setOnClickListener {
            viewModel.getStories()
        }
    }
}