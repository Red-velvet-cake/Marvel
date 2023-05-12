package com.red_velvet.marvel.ui.stories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentStoriesBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class StoriesFragment : BaseFragment<FragmentStoriesBinding, StoriesViewModel>() {

    override val layoutIdFragment = R.layout.fragment_stories
    override val viewModel: StoriesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = StoriesAdapter(emptyList(), viewModel)
        binding.storiesRecyclerView.adapter = adapter
        initNavigateToStoryDetails()
    }

    private fun initNavigateToStoryDetails() {
        viewModel.navigationToStoryDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions = StoriesFragmentDirections.actionStoriesFragmentToStoryFragment(it)
                findNavController().navigate(directions)
            }

        }
    }
}