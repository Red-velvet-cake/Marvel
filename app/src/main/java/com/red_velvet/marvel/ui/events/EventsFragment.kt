package com.red_velvet.marvel.ui.events


import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentEventsBinding
import com.red_velvet.marvel.ui.base.BaseFragment
import com.red_velvet.marvel.ui.utils.Navigation
import com.red_velvet.marvel.ui.utils.onBackPressed


class EventsFragment() : BaseFragment<FragmentEventsBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_events
    override val viewModel: EventsViewModel by viewModels()
    override fun setUp() {
        val adapter = EventsAdapter(emptyList(), viewModel)
        binding.recyclerViewEvents.adapter = adapter

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

    override fun onDestroy() {
        super.onDestroy()
        viewModel.searchQuery.removeObservers(this)
    }
}