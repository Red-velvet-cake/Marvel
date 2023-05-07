package com.red_velvet.marvel.ui.stories

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentStoriesBinding
import com.red_velvet.marvel.ui.base.BaseFragment
import com.red_velvet.marvel.ui.utils.Navigation
import com.red_velvet.marvel.ui.utils.onBackPressed

class StoriesFragment : BaseFragment<FragmentStoriesBinding>() {
    override val layoutIdFragment = R.layout.fragment_stories
    override val viewModel: StoriesViewModel by viewModels()

    override fun setUp() {
        viewModel.getAllStories()
        val adapter = StoriesAdapter(emptyList(), viewModel)
        binding.storiesRecyclerView.adapter = adapter

        onBackPressed {
            viewModel.navigateBack()
        }

        viewModel.navigation.observe(viewLifecycleOwner) {
            when (it) {
                Navigation.Back -> {
                    findNavController().navigateUp()
                }

                is Navigation.Direction -> {
                    findNavController().navigate(it.direction)
                }
            }
        }
    }
}